package org.dubbo.pojo.utils.templatemessage;

import java.util.Map;

public class WxTemplateRequest {

    // 目标用户
    private String touser;
    //模板id
    private String template_id;
    //跳转地址
    private String url;
    //头部颜色
    private String topcolor = "#FF0000";
    //内容
    private Map<String,WxTemplateData> data;

    public WxTemplateRequest(String touser, String template_id, String url, String topcolor, Map<String, WxTemplateData> data) {
        this.touser = touser;
        this.template_id = template_id;
        this.url = url;
        this.topcolor = topcolor;
        this.data = data;
    }

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTopcolor() {
        return topcolor;
    }

    public void setTopcolor(String topcolor) {
        this.topcolor = topcolor;
    }

    public Map getData() {
        return data;
    }

    public void setData(Map data) {
        this.data = data;
    }
}
