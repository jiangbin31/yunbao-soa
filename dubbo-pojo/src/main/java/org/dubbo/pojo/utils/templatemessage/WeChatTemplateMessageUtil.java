package org.dubbo.pojo.utils.templatemessage;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.dubbo.utils.HttpReturn;
import org.dubbo.utils.HttpUtils;

public class WeChatTemplateMessageUtil {

    private static final Logger logger = Logger.getLogger(WeChatTemplateMessageUtil.class);

    public static WxResponse sendWxTemplateMessage(WxTemplateRequest request,String token){
        WxResponse wxResponse = null;
        try{
            String reqUrl = CommonUrl.WX_TEMPLATE_MESSAGE_URL.replaceAll("ACCESS_TOKEN",token);
            HttpReturn post = HttpUtils.post(reqUrl, JSONObject.toJSONString(request));
            wxResponse = JSONObject.parseObject(post.getContent(), WxResponse.class);
            return wxResponse;
        }catch (Exception e){
            wxResponse = new WxResponse("-2",e.getMessage(),"null");
            e.printStackTrace();
        }
        logger.info(JSONObject.toJSONString(wxResponse));
        return wxResponse;
    }

}
