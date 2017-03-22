package service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.wzd.model.entity.Department;
import com.wzd.service.DepartmentService;
import com.wzd.utils.UUIDUtil;

import dao.BasicTest;

public class DepServiceTest extends BasicTest {
	@Autowired
	private DepartmentService service;

	@Test
	public void test1() {
		Department dep = new Department();
//		dep.setId(1);
//		dep.setDeleted(DeleteType.未删除.getValue());
//		dep = service.findTree(dep);
//		System.out.println(dep);
		System.out.println(UUIDUtil.get());
	}
}
