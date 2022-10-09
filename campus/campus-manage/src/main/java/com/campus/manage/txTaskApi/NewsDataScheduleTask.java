package com.campus.manage.txTaskApi;

import com.campus.common.utils.http.HttpUtils;
import com.campus.manage.domain.CamNews;
import com.campus.manage.service.ICamNewsService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yyy
 * @version 1.0
 * @date 2022/9/24 13:22
 */
@Component("NewsDataScheduleTask")
public class NewsDataScheduleTask {


    @Autowired
    private ICamNewsService newsService;

    /**
     * 新闻数据爬取，存库
     * @throws Exception
     */
    @Scheduled(cron = "0 50 9 * * *")
    public void getData() throws Exception {

        //前缀
        final String url_prefix = "https://www.hniu.cn";

        String html = HttpUtils.sendGet("https://www.hniu.cn/xwzx/zhxw.htm");
        Document document = Jsoup.parse(html);
        Elements newsElement = document.getElementsByClass("dqlb");
        Document newsDocument = Jsoup.parse(String.valueOf(newsElement));
        Elements li = newsDocument.getElementsByTag("li");
        //新闻时间html
        Elements newsTime = Jsoup.parse(String.valueOf(li)).getElementsByClass("fr");
        //新闻标题html
        Elements newsTitle = Jsoup.parse(String.valueOf(li)).getElementsByAttribute("title");
        //新闻跳转链接数据html
        Elements newsHref = Jsoup.parse(String.valueOf(li)).getElementsByTag("a");

        List<CamNews> list = new ArrayList<>();


        for (int i = 0; i < newsTime.size(); i++) {
            String newtime = newsTime.get(i).text();        //获取新闻事件
            String newtitle = newsTitle.get(i).text();      //获取新闻标题

            //获取每条新闻跳转的连接
            String href = newsHref.get(i).attr("href").replace("..", "https://www.hniu.cn");
            String contenthtml = HttpUtils.sendGet(href);

            //新闻顶部内容（作者、标题、来源等）
            Elements xqnr_tit = Jsoup.parse(contenthtml).getElementsByClass("xqnr_tit");
            Elements spancontents = Jsoup.parse(String.valueOf(xqnr_tit)).getElementsByTag("span");
            String author = spancontents.get(1).text().replace("作者：","");
            String source = spancontents.get(2).text().replace("来源：","");
            String editor = spancontents.get(3).text().replace("编辑：","");
            String updateby = spancontents.get(4).text().replace("审核人：","");

            //逆向寻找  点击数 ajax函数
            String[] scripts = spancontents.get(5).getElementsByTag("script")
                    .html()
                    .replace("_showDynClicks(\"wbnews\", ", "")
                    .replace(")", "")
                    .split(", ");

            String clickid = scripts[1];
            String owner = scripts[0];
            String ajax_click = "https://www.hniu.cn/system/resource/code/news/click/dynclicks.jsp?clickid="+ clickid +"&owner="+ owner +"&clicktype=wbnews";
            //点击数
            String hits = HttpUtils.sendGet(ajax_click);

            //新闻内容
            Elements v_news_content = Jsoup.parse(contenthtml).getElementsByClass("v_news_content");
            //p标签数据
            Elements pcontents = Jsoup.parse(String.valueOf(v_news_content)).getElementsByTag("p");
            StringBuffer contentstr = new StringBuffer("");
            for (int j = 0; j <pcontents.size(); j++) {
                contentstr.append(pcontents.get(j).text());
                contentstr.append("\n");
            }

            Elements imgcontent = Jsoup.parse(String.valueOf(pcontents)).getElementsByTag("img");

            StringBuffer imgstr = new StringBuffer("");
            for (int j = 0; j < imgcontent.size(); j++) {
                String imgs = imgcontent.get(j).attr("src");
                if (imgstr.toString().isEmpty()){
                    imgstr.append(url_prefix);
                    imgstr.append(imgs);
                }else {
                    imgstr.append(",");
                    imgstr.append(url_prefix);
                    imgstr.append(imgs);
                }
            }

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            CamNews camNews = new CamNews();
            camNews.setNewTitle(newtitle);
            camNews.setCreateTime(format.parse(newtime));
            camNews.setNewAuthor(author);
            camNews.setNewContent(contentstr.toString());
            camNews.setImage(imgstr.toString());
            camNews.setNewSource(source);
            camNews.setNewHits(Long.parseLong(hits));
            camNews.setCreateBy(editor);
            camNews.setUpdateBy(updateby);

            list.add(camNews);

            //System.out.println(imgstr);
            //System.out.println("-------------------------------------");

        }
        //System.out.println(list);
        newsService.insertBatch(list);
    }
}
