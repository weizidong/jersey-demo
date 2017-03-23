package service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.wzd.service.wechat.QyWxService;

import dao.BasicTest;

public class DepServiceTest extends BasicTest {
	@Autowired
	private QyWxService service;

	@Test
	public void test1() {
		service.sync();
	}
}
