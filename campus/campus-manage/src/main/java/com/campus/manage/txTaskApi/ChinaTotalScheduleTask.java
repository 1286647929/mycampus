package com.campus.manage.txTaskApi;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.campus.common.utils.http.HttpUtils;
import com.campus.manage.domain.CamNovChinaTotal;
import com.campus.manage.domain.CamNovData;
import com.campus.manage.service.ICamNovChinaTotalService;
import com.campus.manage.service.ICamNovDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    private ICamNovDataService novDataService;

    /**
     * 更新中国疫情总体数据、以及更新中国34省份疫情数据
     */
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

        //进行实体类赋值
        CamNovChinaTotal camNovChinaTotal = new CamNovChinaTotal();

        camNovChinaTotal.setConfirm(Long.parseLong(String.valueOf(confirm)));
        camNovChinaTotal.setSuspect(Long.parseLong(String.valueOf(suspect)));
        camNovChinaTotal.setHeal(Long.parseLong(String.valueOf(heal)));
        camNovChinaTotal.setDead(Long.parseLong(String.valueOf(dead)));
        camNovChinaTotal.setSevere(Long.parseLong(String.valueOf(severe)));
        camNovChinaTotal.setInput(Long.parseLong(String.valueOf(input)));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        camNovChinaTotal.setUpdateTime(format.parse(String.valueOf(lastUpdateTime)));

        //插入数据库
        chinaTotalService.insertChinaTotal(camNovChinaTotal);

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
