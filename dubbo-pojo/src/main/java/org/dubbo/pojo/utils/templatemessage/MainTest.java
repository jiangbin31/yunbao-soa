package org.dubbo.pojo.utils.templatemessage;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainTest {


    @Test
    public void test1(){
        String token = "12_JRFFkU1qDy_lDGJ6u8Y0GATHv2xOTh9cYTUc02hutowLHZOtSDSB8Lo6OkoFBV8BYerbpi_4NQHsK67azbiWCz6nXImeOB1GofxLpc4Da8oIF7h5-LF3wSJU8ZSQApzwXCZq-dRrBLmfzOi6HHQlCDAPPY";
        String reqUrl = CommonUrl.WX_TEMPLATE_MESSAGE_URL.replaceAll("ACCESS_TOKEN",token);
        System.out.println(reqUrl);
        String openId = "oVFOhjqy-0pZs35YNqP58LgfnrPs";
        String templateId = "YLqKvcEn0TnV43x32kMg6cbtXReUBh3HJfyYxG_iDww";
        String url = "http://www.baidu.com";
        String color = "#FF0000";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss");
        String time = simpleDateFormat.format(new Date());
        Map<String,WxTemplateData> dataMap = new HashMap<>();
        dataMap.put("first",new WxTemplateData("七夕绿色警报","#00ff00"));
        dataMap.put("keyword1",new WxTemplateData("姜先生","#00ff00"));
        dataMap.put("keyword2",new WxTemplateData("萧山","#00ff00"));
        dataMap.put("keyword3",new WxTemplateData("2018/08/17 22:00:00","#00ff00"));
        dataMap.put("remark",new WxTemplateData("总统套房为爱鼓掌","#00ff00"));
        WxTemplateRequest request = new WxTemplateRequest(openId,templateId,url,color,dataMap);
        System.out.println(JSONObject.toJSONString(request));
        WeChatTemplateMessageUtil.sendWxTemplateMessage(request,token);
    }

}
