package dao;

import org.junit.Test;

import com.wzd.model.dao.UserDao;

public class DaoTest {
	@Test
	public void insertTest() {
		UserDao dao = new UserDao();
		dao.insert();
	}
}
