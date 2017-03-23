package wechat;

import org.junit.Test;

import com.wzd.service.wechat.department.WxDepService;
import com.wzd.service.wechat.msg.WxMsg;
import com.wzd.service.wechat.msg.WxMsgSender;

public class QyWeiXin {
	@Test
	public void senmsg() {
		WxMsg msg = WxMsg.generateText("oFTpnwnsF7Vv6WkM_fySqDtD-rEo", "测试发生消息");
		WxMsgSender.sendTextToFw(msg);
	}

	@Test
	public void getDep() {
		WxDepService service = new WxDepService();
		System.out.println(service.getDepList(null));
	}

}
