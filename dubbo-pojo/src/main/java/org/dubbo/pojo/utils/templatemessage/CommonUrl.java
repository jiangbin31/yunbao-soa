package org.dubbo.pojo.utils.templatemessage;

import java.util.HashMap;
import java.util.Map;

public class CommonUrl {

    //微信模板消息请求地址
    public static String WX_TEMPLATE_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";

    //获奖实物商品尺码选择跳转地址
    public static String ACTIVITY_CHECK_SIZE_URL = "http://jnby.jnby.com/wnhz/mall/active/winningInformation/goodDetail.html?version=1&goodsid=GOODSID&skcno=SKCNO&activeId=ACTIVITYID&prizeId=PRIZEID&unionid=UNIONID&openid=OPENID&weid=WEID&winResultId=WINRESULTID";

    public static Map<String,String> wxConfigMap(){
        Map<String,String> map=new HashMap<>();
        map.put("JNBY","wx78d38a41cc2b7b64");
        map.put("CROQUIS","wxb1438c4a43b9ce4e");
        map.put("less","wx5d38aa6c5fb20347");
        map.put("jnby by JNBY","wx8d35e39ac5ad14e7");
        map.put("Pomme de terre","wx8804ca7de1ac92ec");
        map.put("SAMO","wx3234d4b7e7e1dc40");
        map.put("JNBYHOME","wxe26829825f17f05e");
        map.put("LASU MIN SOLA","wxa57d428835563776");
        return map;
    }

    public static Map<String,String> wxConfigWeidToAppidMap(){
        Map<String,String> map=new HashMap<>();
        map.put("2738574294","wx78d38a41cc2b7b64");
        map.put("2504948039","wxb1438c4a43b9ce4e");
        map.put("2822095692","wx5d38aa6c5fb20347");
        map.put("4","wx8d35e39ac5ad14e7");
        map.put("6924108367","wx8804ca7de1ac92ec");
        map.put("11","wx3234d4b7e7e1dc40");
        map.put("8348044436","wxe26829825f17f05e");
        map.put("5","wxa57d428835563776");
        return map;
    }

    public static Map<String,String> wxConfigWeidToTemplateIdMap(){
        Map<String,String> map=new HashMap<>();
        map.put("2738574294","6Ygsi4KXvlUPHpRm1Gtl-SJ0kgbwTYI123V9Ib1G8Ng");
        map.put("2504948039","0IgwzIjqQkl42_wQ1qJwkjs2PYnsesFb5zMFyYKpZRk");
        map.put("2822095692","0rcgPsBGzIzcp9wjbLwW3gxJ6it-3gjl_9PGZ3KYJdw");
        map.put("4","5t0czdwR6KVH-APsaaYddLOf68HILbnmhRZVpSFB4IA");
        map.put("6924108367","NjwtHFO-1hnXQDy0yJmF5dScIfasdsXPJtvltYZZB6g");
        map.put("11","wx3234d4b7e7e1dc40");
        map.put("8348044436","FINb8y51AcSo0XpzQ0z_ToDa4mNwChAtJ_vsqZRorpk");
        map.put("5","wxa57d428835563776");
        return map;

    }


    public static Map<String,String> wxMessageTemplate(){
        Map<String,String> map=new HashMap<>();
        map.put("JNBY","6Ygsi4KXvlUPHpRm1Gtl-SJ0kgbwTYI123V9Ib1G8Ng");
        map.put("CROQUIS","0IgwzIjqQkl42_wQ1qJwkjs2PYnsesFb5zMFyYKpZRk");
        map.put("less","0rcgPsBGzIzcp9wjbLwW3gxJ6it-3gjl_9PGZ3KYJdw");
        map.put("jnby by JNBY","5t0czdwR6KVH-APsaaYddLOf68HILbnmhRZVpSFB4IA");
        map.put("Pomme de terre","NjwtHFO-1hnXQDy0yJmF5dScIfasdsXPJtvltYZZB6g");
        map.put("SAMO","wx3234d4b7e7e1dc40");
        map.put("JNBYHOME","FINb8y51AcSo0XpzQ0z_ToDa4mNwChAtJ_vsqZRorpk");
        map.put("LASU MIN SOLA","wxa57d428835563776");
        return map;

    }

}
