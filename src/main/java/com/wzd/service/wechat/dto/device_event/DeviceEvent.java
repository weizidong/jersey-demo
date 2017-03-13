package com.lifesense.healthcenter.service.wechat.dto.device_event;

import com.lifesense.healthcenter.service.wechat.constants.MsgType;
import com.lifesense.healthcenter.service.wechat.dto.BaseEvent;



public class DeviceEvent extends BaseEvent {

    private String event;

    public DeviceEvent() {
        setMsgType(MsgType.DEVICE_EVENT);
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

}
