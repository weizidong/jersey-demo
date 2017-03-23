package dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.wzd.model.dao.DepartmentDao;
import com.wzd.model.entity.Department;

public class DepDaoTest extends BasicTest {
	@Autowired
	private DepartmentDao dao;

	@Test
	public void test1() {
		List<Department> list = dao.findAll();
		list.forEach(dep -> {
			System.out.println(dep);
		});
	}

	@Test
	public void text2() {
		String s = "123123156465465.jpg";
		System.out.println(s.substring(0, s.length() - 4));
	}
}
