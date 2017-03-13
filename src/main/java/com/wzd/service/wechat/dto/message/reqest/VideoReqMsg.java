package com.lifesense.healthcenter.service.wechat.dto.message.reqest;

import com.lifesense.healthcenter.service.wechat.constants.MsgType;
import com.lifesense.healthcenter.service.wechat.dto.BaseReqMsg;

public final class VideoReqMsg extends BaseReqMsg {

    private String mediaId;
    private String thumbMediaId;

    public VideoReqMsg(String mediaId, String thumbMediaId) {
        super();
        this.mediaId = mediaId;
        this.thumbMediaId = thumbMediaId;
        setMsgType(MsgType.VIDEO);
    }

    public String getMediaId() {
        return mediaId;
    }

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    @Override
    public String toString() {
        return "VideoReqMsg [mediaId=" + mediaId + ", thumbMediaId="
                + thumbMediaId + ", toUserName=" + toUserName
                + ", fromUserName=" + fromUserName + ", createTime="
                + createTime + ", msgType=" + msgType + ", msgId=" + msgId
                + "]";
    }

}
