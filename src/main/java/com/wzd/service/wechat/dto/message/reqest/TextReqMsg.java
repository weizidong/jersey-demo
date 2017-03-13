package com.lifesense.healthcenter.service.wechat.dto.message.reqest;

import com.lifesense.healthcenter.service.wechat.constants.MsgType;
import com.lifesense.healthcenter.service.wechat.dto.BaseReqMsg;

public final class TextReqMsg extends BaseReqMsg {

    private String content;

    public TextReqMsg(String content) {
        super();
        this.content = content;
        setMsgType(MsgType.TEXT);
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "TextReqMsg [content=" + content + ", toUserName=" + toUserName
                + ", fromUserName=" + fromUserName + ", createTime="
                + createTime + ", msgType=" + msgType + ", msgId=" + msgId
                + "]";
    }

}
