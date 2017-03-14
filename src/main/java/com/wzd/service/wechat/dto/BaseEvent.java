package com.wzd.service.wechat.dto;

import com.wzd.service.wechat.constants.MsgType;

public class BaseEvent extends BaseReq {

    private String event;

    public BaseEvent() {
        setMsgType(MsgType.EVENT);
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

}
