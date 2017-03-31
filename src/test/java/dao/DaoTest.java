package dao;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class DaoTest extends BasicTest {
//	@Autowired
//	private HistoryDao historyDao;

	@Test
	public void test1() {
		System.out.println(StringUtils.contains("|asdasd|name|zxczxc|", "|name|"));
	}
}
