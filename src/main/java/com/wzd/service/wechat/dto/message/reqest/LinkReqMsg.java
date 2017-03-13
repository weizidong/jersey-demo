package com.lifesense.healthcenter.service.wechat.dto.message.reqest;

import com.lifesense.healthcenter.service.wechat.constants.MsgType;
import com.lifesense.healthcenter.service.wechat.dto.BaseReqMsg;

public final class LinkReqMsg extends BaseReqMsg {

    private String title;
    private String description;
    private String url;

    public LinkReqMsg(String title, String description, String url) {
        super();
        this.title = title;
        this.description = description;
        this.url = url;
        setMsgType(MsgType.EVENT);
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "LinkReqMsg [title=" + title + ", description=" + description
                + ", url=" + url + ", toUserName=" + toUserName
                + ", fromUserName=" + fromUserName + ", createTime="
                + createTime + ", msgType=" + msgType + ", msgId=" + msgId
                + "]";
    }

}
