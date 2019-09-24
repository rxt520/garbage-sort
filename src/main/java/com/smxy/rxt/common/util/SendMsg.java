package com.smxy.rxt.common.util;

import com.alibaba.fastjson.JSONException;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import javax.xml.ws.http.HTTPException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class SendMsg {
    public SendMsg() {
    }
    public String getCode(String phoneNumber){
        String Code = RandomStringUtils.random(6,false,true);
        Integer appid = 1400252587;
        String appkey = "4ce29e5232ece192c7fd6d568ab4239b";
        Integer templateId = 409423;
        try {
            String[] params = {Code,"5"};
            SmsSingleSender ssender = new SmsSingleSender(appid, "4ce29e5232ece192c7fd6d568ab4239b");
            SmsSingleSenderResult result = ssender.sendWithParam("86",phoneNumber,templateId, params,"", "", "");
            System.out.println(result);
        } catch (HTTPException e) {
            // HTTP 响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // JSON 解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络 IO 错误
            e.printStackTrace();
        } catch (com.github.qcloudsms.httpclient.HTTPException e) {
            e.printStackTrace();
        }
        return Code;
    }

    public void setRedis(String phone,String code,StringRedisTemplate redisTemplate){
        redisTemplate.opsForValue().set("mobile_code:"+phone,code);
        redisTemplate.expire("mobile_code:"+phone,300, TimeUnit.SECONDS);
    }
    public String getRedis(String phone,StringRedisTemplate redisTemplate){
        return redisTemplate.opsForValue().get("mobile_code:"+phone);
    }

}
