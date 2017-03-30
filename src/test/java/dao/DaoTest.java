package dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.wzd.model.dao.HistoryDao;
import com.wzd.web.param.PageParam;

public class DaoTest extends BasicTest {
	@Autowired
	private HistoryDao historyDao;

	@Test
	public void test1() {
		System.out.println(historyDao.getSignList(new PageParam()));
	}
}
