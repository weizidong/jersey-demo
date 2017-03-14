package com.wzd.service.wechat.dto.message.reqest;

import com.wzd.service.wechat.constants.MsgType;
import com.wzd.service.wechat.dto.BaseReqMsg;

public final class LocationReqMsg extends BaseReqMsg {

    private double locationX;
    private double locationY;
    private int    scale;
    private String label;

    public LocationReqMsg(double locationX, double locationY, int scale,
                          String label) {
        super();
        this.locationX = locationX;
        this.locationY = locationY;
        this.scale = scale;
        this.label = label;
        setMsgType(MsgType.LOCATION);
    }

    public double getLocationX() {
        return locationX;
    }

    public double getLocationY() {
        return locationY;
    }

    public int getScale() {
        return scale;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return "LocationReqMsg [locationX=" + locationX + ", locationY="
                + locationY + ", scale=" + scale + ", label=" + label
                + ", toUserName=" + toUserName + ", fromUserName="
                + fromUserName + ", createTime=" + createTime + ", msgType="
                + msgType + ", msgId=" + msgId + "]";
    }

}
