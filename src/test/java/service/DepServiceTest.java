package service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.wzd.model.entity.Department;
import com.wzd.service.DepartmentService;

import dao.BasicTest;

public class DepServiceTest extends BasicTest {
	@Autowired
	private DepartmentService service;

	@Test
	public void test1() {
		Department dep = new Department();
		dep.setId(1);
		dep = service.findTree(dep);
		System.out.println(dep);
	}
}
