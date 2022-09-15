package com.campus.manage.txTaskApi;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.campus.common.utils.http.HttpUtils;
import com.campus.manage.domain.CamNovChinaTotal;
import com.campus.manage.domain.CamNovChinaTotalToday;
import com.campus.manage.domain.CamNovData;
import com.campus.manage.service.ICamNovChinaTotalService;
import com.campus.manage.service.ICamNovChinaTotalTodayService;
import com.campus.manage.service.ICamNovDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 网易疫情数据api
 *
 * @author yyy
 * @date 2022/9/5 16:09
 */
@Component("ChinaTotalScheduleTask")
public class ChinaTotalScheduleTask {

    @Autowired
    private ICamNovChinaTotalService chinaTotalService;

    @Autowired
    private ICamNovChinaTotalTodayService chinaTotalTodayService;

    @Autowired
    private ICamNovDataService novDataService;

    /**
     * 更新中国疫情总体数据、以及更新中国34省份疫情数据
     */
    @Scheduled(cron = "0 10 */5 * * *")
    public void updateChinaTotalToDB() throws ParseException {
        String data = HttpUtils.sendGet("https://c.m.163.com/ug/api/wuhan/app/data/list-total");
        JSONObject jsonObject = JSONObject.parseObject(data);
        Object data1 = jsonObject.get("data");
        //updateTime
        Object lastUpdateTime = JSONObject.parseObject(String.valueOf(data1)).get("lastUpdateTime");
        System.out.println("lastUpdateTime："+lastUpdateTime);
        //chinaTotal数据部分
        Object chinaTotal = JSONObject.parseObject(String.valueOf(data1)).get("chinaTotal");
        //当前总数的数据
        Object CurrentTotal = JSONObject.parseObject(String.valueOf(chinaTotal)).get("total");
        //当前总确诊数
        Object confirm = JSONObject.parseObject(String.valueOf(CurrentTotal)).get("confirm");
        //当前疑似病例
        Object suspect = JSONObject.parseObject(String.valueOf(CurrentTotal)).get("suspect");
        //当前治愈数
        Object heal = JSONObject.parseObject(String.valueOf(CurrentTotal)).get("heal");
        //当前死亡数
        Object dead = JSONObject.parseObject(String.valueOf(CurrentTotal)).get("dead");
        //当前严重数
        Object severe = JSONObject.parseObject(String.valueOf(CurrentTotal)).get("severe");
        //当前输入数
        Object input = JSONObject.parseObject(String.valueOf(CurrentTotal)).get("input");


        //额外数据（无症状感染者）
        Object extData = JSONObject.parseObject(String.valueOf(chinaTotal)).get("extData");
        //无症状感染者总数
        Object noSymptom = JSONObject.parseObject(String.valueOf(extData)).get("noSymptom");
        //较昨日增长数
        Object incrNoSymptom = JSONObject.parseObject(String.valueOf(extData)).get("incrNoSymptom");

        //进行实体类赋值
        CamNovChinaTotal camNovChinaTotal = new CamNovChinaTotal();

        camNovChinaTotal.setConfirm(Long.parseLong(String.valueOf(confirm == null ? 0 : confirm)));
        camNovChinaTotal.setSuspect(Long.parseLong(String.valueOf(suspect == null ? 0 : suspect)));
        camNovChinaTotal.setHeal(Long.parseLong(String.valueOf(heal == null ? 0 : heal)));
        camNovChinaTotal.setDead(Long.parseLong(String.valueOf(dead == null ? 0 : dead)));
        camNovChinaTotal.setSevere(Long.parseLong(String.valueOf(severe == null ? 0 : severe)));
        camNovChinaTotal.setInput(Long.parseLong(String.valueOf(input == null ? 0 : input)));
        camNovChinaTotal.setNoSymptom(Long.parseLong(String.valueOf(noSymptom == null ? 0 : noSymptom)));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        camNovChinaTotal.setUpdateTime(format.parse(String.valueOf(lastUpdateTime == null ? new Date() : lastUpdateTime)));

        //插入数据库
        chinaTotalService.insertChinaTotal(camNovChinaTotal);


        //当日的总数据
        Object today = JSONObject.parseObject(String.valueOf(chinaTotal)).get("today");
        Object todayconfirm = JSONObject.parseObject(String.valueOf(today)).get("confirm");
        Object todaysuspect = JSONObject.parseObject(String.valueOf(today)).get("suspect");
        Object todayheal = JSONObject.parseObject(String.valueOf(today)).get("heal");
        Object todaydead = JSONObject.parseObject(String.valueOf(today)).get("dead");
        Object todaysevere = JSONObject.parseObject(String.valueOf(today)).get("severe");
        Object todayinput = JSONObject.parseObject(String.valueOf(today)).get("input");
        Object todaystoreconfirm = JSONObject.parseObject(String.valueOf(today)).get("storeConfirm");

        CamNovChinaTotalToday camNovChinaTotalToday = new CamNovChinaTotalToday();
        //当日确诊总数
        camNovChinaTotalToday.setConfirm(Long.parseLong(String.valueOf(todayconfirm == null ? 0 : todayconfirm)));
        //当日疑似病例
        camNovChinaTotalToday.setSuspect(Long.parseLong(String.valueOf(todaysuspect == null ? 0 : todaysuspect)));
        //当日治愈数
        camNovChinaTotalToday.setHeal(Long.parseLong(String.valueOf(todayheal == null ? 0 : todayheal)));
        //当日死亡数
        camNovChinaTotalToday.setDead(Long.parseLong(String.valueOf(todaydead == null ? 0 : todaydead)));
        //当日严重数
        camNovChinaTotalToday.setSevere(Long.parseLong(String.valueOf(todaysevere == null ? 0 :todaysevere)));
        //当日输入数
        camNovChinaTotalToday.setInput(Long.parseLong(String.valueOf(todayinput == null ? 0 :todayinput)));
        //较昨日增长无症状感染者
        camNovChinaTotalToday.setIncrNoSymptom(Long.parseLong(String.valueOf(incrNoSymptom == null ? 0 : incrNoSymptom)));
        //当日现有确诊
        camNovChinaTotalToday.setStoreconfirm(Long.parseLong(String.valueOf(todaystoreconfirm == null ? 0 : todaystoreconfirm)));

        //插入数据库
        chinaTotalTodayService.insertChinaTotalToday(camNovChinaTotalToday);


        //34个省份疫情数据
        Object areaTree = JSONObject.parseObject(String.valueOf(data1)).get("areaTree");
        JSONArray array = JSONArray.parseArray(String.valueOf(areaTree));
        Object chinaTree = array.get(2);
        Object children = JSONObject.parseObject(String.valueOf(chinaTree)).get("children");
        JSONArray childrenArray = JSONArray.parseArray(String.valueOf(children));
        List<CamNovData> list = new ArrayList<>();
        for (int i = 0; i < childrenArray.size(); i++) {
            Object name = JSONObject.parseObject(String.valueOf(childrenArray.get(i))).get("name");
            Object total = JSONObject.parseObject(String.valueOf(childrenArray.get(i))).get("total");
            Object provinceconfirm = JSONObject.parseObject(String.valueOf(total)).get("confirm");
            Object provinceheal = JSONObject.parseObject(String.valueOf(total)).get("heal");
            Object provincedead = JSONObject.parseObject(String.valueOf(total)).get("dead");
            //现在确诊数
            Long nowconfirm = Long.parseLong(String.valueOf(provinceconfirm)) - Long.parseLong(String.valueOf(provinceheal)) - Long.parseLong(String.valueOf(provincedead));
            //最后更新时间
            Object updateTime = JSONObject.parseObject(String.valueOf(childrenArray.get(i))).get("lastUpdateTime");
            //实体类
            CamNovData camNovData = new CamNovData();
            camNovData.setName(String.valueOf(name));
            camNovData.setValue(nowconfirm);
            camNovData.setUpdateTime(format.parse(String.valueOf(updateTime)));
            list.add(camNovData);
        }
        //将实体类插入数据库
        novDataService.insertCamNovData(list);
    }
}
