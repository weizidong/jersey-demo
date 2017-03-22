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

}
