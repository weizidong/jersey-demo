package com.lifesense.healthcenter.service.wechat.dto.message.response;

import com.lifesense.healthcenter.service.wechat.constants.MsgType;
import com.lifesense.healthcenter.service.wechat.dto.BaseMsg;
import com.lifesense.healthcenter.service.wechat.dto.MessageBuilder;


/**
 * @author peiyu
 */
public class VoiceMsg extends BaseMsg {

    private String mediaId;

    public VoiceMsg(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    @Override
    public String toXml() {
        MessageBuilder mb = new MessageBuilder(super.toXml());
        mb.addData("MsgType", MsgType.VOICE);
        mb.append("<Voice>\n");
        mb.addData("MediaId", mediaId);
        mb.append("</Voice>\n");
        mb.surroundWith("xml");
        return mb.toString();
    }

}
