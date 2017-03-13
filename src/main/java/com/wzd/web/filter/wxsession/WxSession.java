package com.wzd.web.filter.wxsession;

import java.io.Serializable;

@SuppressWarnings("serial")
public class WxSession implements Serializable {

    // 会话ID
    private String sessionId;

    // 微信openId
    private String openId;

    // 家庭帐号ID
    private String accountId;

    // 时间戳
    private long ts;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    @Override
    public String toString() {
        return "WxSession [sessionId=" + sessionId + ", openId=" + openId + ", accountId=" + accountId + ", ts=" + ts + "]";
    }

}
