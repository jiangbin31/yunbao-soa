package org.dubbo.pojo.utils.templatemessage;

/*微信模板消息请求数据之数据内容数据结构*/
public class WxTemplateData {

    /*用户名称*/
    String value;
    /*颜色*/
    String color;

    public WxTemplateData() {
    }

    public WxTemplateData(String value, String color) {
        this.value = value;
        this.color = color;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
