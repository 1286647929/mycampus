package com.campus.manage.controller;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.campus.common.annotation.Anonymous;
import com.campus.common.core.domain.AjaxResult;
import com.campus.common.utils.http.HttpUtils;
import com.campus.common.utils.ip.AddressUtils;
import com.campus.manage.domain.CamNovLocalTodayTotalData;
import com.campus.manage.domain.CamNovLocalTotalData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 当地疫情数据
 *
 * @author yyy
 * @date 2022/9/12 16:19
 */
@RestController
@RequestMapping("/campus/novlocaltotal")
//@Anonymous
public class CamNovLocalTotalController {

    @GetMapping("/list")
    public AjaxResult getlocaltotal(){
        List<String> address = AddressUtils.getAddress();
        //34个省份疫情数据
        String data = HttpUtils.sendGet("https://c.m.163.com/ug/api/wuhan/app/data/list-total");
        JSONObject jsonObject = JSONObject.parseObject(data);   //data数据解析
        Object data1 = jsonObject.get("data");
        Object areaTree = JSONObject.parseObject(String.valueOf(data1)).get("areaTree");
        JSONArray array = JSONArray.parseArray(String.valueOf(areaTree));
        Object chinaTree = array.get(2);    //中国省份数据解析
        Object children = JSONObject.parseObject(String.valueOf(chinaTree)).get("children");    //中国34个省份
        //34个省份
        JSONArray childrenArray = JSONArray.parseArray(String.valueOf(children));
        List<Object> objectList = new ArrayList<Object>();
        for (int i = 0; i < childrenArray.size(); i++) {
            Object name = JSONObject.parseObject(String.valueOf(childrenArray.get(i))).get("name");
            if (address.get(0).contains(String.valueOf(name))){
                Object lastupdateTime = JSONObject.parseObject(String.valueOf(childrenArray.get(i))).get("lastUpdateTime");
                Object total = JSONObject.parseObject(String.valueOf(childrenArray.get(i))).get("total");
                Object confirm = JSONObject.parseObject(String.valueOf(total)).get("confirm");
                Object dead = JSONObject.parseObject(String.valueOf(total)).get("dead");
                Object heal = JSONObject.parseObject(String.valueOf(total)).get("heal");

                //当地疫情总体数据——实体类
                CamNovLocalTotalData camNovLocalTotalData = new CamNovLocalTotalData();
                camNovLocalTotalData.setName(address.get(0));
                camNovLocalTotalData.setConfirm(Long.parseLong(String.valueOf(confirm)));
                camNovLocalTotalData.setDead(Long.parseLong(String.valueOf(dead)));
                camNovLocalTotalData.setHeal(Long.parseLong(String.valueOf(heal)));
                camNovLocalTotalData.setUpdateTime(String.valueOf(lastupdateTime));

                objectList.add(camNovLocalTotalData);

                Object today = JSONObject.parseObject(String.valueOf(childrenArray.get(i))).get("today");
                Object confirm1 = JSONObject.parseObject(String.valueOf(today)).get("confirm");
                Object head1 = JSONObject.parseObject(String.valueOf(today)).get("heal");
                Object dead1 = JSONObject.parseObject(String.valueOf(today)).get("dead");

                //当地疫情较昨日数据——实体类
                CamNovLocalTodayTotalData camNovLocalTodayTotalData = new CamNovLocalTodayTotalData();
                camNovLocalTodayTotalData.setName(address.get(1));
                camNovLocalTodayTotalData.setConfirm(Long.parseLong(String.valueOf(confirm1)));
                camNovLocalTodayTotalData.setHeal(Long.parseLong(String.valueOf(head1)));
                camNovLocalTodayTotalData.setDead(Long.parseLong(String.valueOf(dead1)));

                objectList.add(camNovLocalTodayTotalData);


            }else {
                continue;
            }
        }

        return AjaxResult.success(objectList);
    }
}
