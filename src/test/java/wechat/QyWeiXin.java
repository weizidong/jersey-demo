package wechat;

import org.junit.Test;

import com.wzd.service.wechat.base.MsgType;
import com.wzd.service.wechat.department.WxDepService;
import com.wzd.service.wechat.msg.WxMsg;
import com.wzd.service.wechat.msg.WxMsgSender;
import com.wzd.service.wechat.msg.dto.TEXT;

public class QyWeiXin {
	// @Test
	public void senmsg() {
		TEXT text = new TEXT();
		text.setContent("测试发送文本");
		WxMsg msg = new WxMsg();
		msg.setTouser("weizidong");
		msg.setMsgtype(MsgType.TEXT);
		msg.setAgentid(1);
		msg.setText(text);
		WxMsgSender.sendTextToQy(msg);
	}

	@Test
	public void getDep() {
		WxDepService service = new WxDepService();
		System.out.println(service.getDepList(null));
	}

}
