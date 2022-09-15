package com.campus.common.utils.ip;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.campus.common.config.CampusConfig;
import com.campus.common.constant.Constants;
import com.campus.common.utils.StringUtils;
import com.campus.common.utils.http.HttpUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 获取地址类
 * 
 * @author campus
 */
public class AddressUtils
{
    private static final Logger log = LoggerFactory.getLogger(AddressUtils.class);

    // IP地址查询
    public static final String IP_URL = "http://whois.pconline.com.cn/ipJson.jsp";

    // 未知地址
    public static final String UNKNOWN = "XX XX";

    public static String getRealAddressByIP(String ip)
    {
        // 内网不查询
        if (IpUtils.internalIp(ip))
        {
            return "内网IP";
        }
        if (CampusConfig.isAddressEnabled())
        {
            try
            {
                String rspStr = HttpUtils.sendGet(IP_URL, "ip=" + ip + "&json=true", Constants.GBK);
                if (StringUtils.isEmpty(rspStr))
                {
                    log.error("获取地理位置异常 {}", ip);
                    return UNKNOWN;
                }
                JSONObject obj = JSON.parseObject(rspStr);
                String region = obj.getString("pro");
                String city = obj.getString("city");
                return String.format("%s %s", region, city);
            }
            catch (Exception e)
            {
                log.error("获取地理位置异常 {}", ip);
            }
        }
        return UNKNOWN;
    }

    /**
     * 内网查询
     * @return
     */
    public static List<String> getAddress(){
        try
        {
            String rspStr = HttpUtils.sendGet(IP_URL, "json=true", Constants.GBK);
            if (StringUtils.isEmpty(rspStr))
            {
                log.error("获取地理位置异常 {}");
                return Collections.singletonList(UNKNOWN);
            }
            JSONObject obj = JSON.parseObject(rspStr);
            String region = obj.getString("pro");
            String city = obj.getString("city");
            List pro = new ArrayList<String>();
            pro.add(region);
            pro.add(city);
            return pro;
        }
        catch (Exception e)
        {
            log.error("获取地理位置异常 {}");
        }
        return Collections.singletonList(UNKNOWN);
    }
}
