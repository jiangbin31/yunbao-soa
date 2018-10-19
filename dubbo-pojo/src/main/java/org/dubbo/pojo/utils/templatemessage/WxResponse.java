package org.dubbo.pojo.utils.templatemessage;

public class WxResponse {

    private String errcode;
    private String errmsg;
    private String msgid;

    public WxResponse() {
    }

    public WxResponse(String errcode, String errmsg, String msgid) {
        this.errcode = errcode;
        this.errmsg = errmsg;
        this.msgid = msgid;
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getMsgid() {
        return msgid;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }
}