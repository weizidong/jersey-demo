package dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.wzd.model.dao.DepartmentDao;
import com.wzd.model.dao.HistoryDao;
import com.wzd.model.entity.Department;
import com.wzd.model.entity.History;
import com.wzd.model.enums.DeleteType;
import com.wzd.model.enums.HistoryType;

public class DepDaoTest extends BasicTest {
	@Autowired
	private DepartmentDao departmentDao;
	@Autowired
	private HistoryDao historyDao;

	@Test
	public void test1() {
		List<Department> list = departmentDao.findAll(DeleteType.未删除);
		list.forEach(dep -> {
			System.out.println(dep);
		});
	}

	@Test
	public void text2() {
		String s = "123123156465465.jpg";
		System.out.println(s.substring(0, s.length() - 4));
	}

	@Test
	public void test3() {
		History h = new History();
		h.setContent("积分签到1");
		h.setScore(50);
		h.setTitle("积分签到1");
		h.setUserId("d025138d9b574ccd9b2736bfe16748f6");
		h.setType(HistoryType.积分签到.getValue());
		historyDao.create(h);
		System.out.println(historyDao.list("d025138d9b574ccd9b2736bfe16748f6", DeleteType.未删除));
	}

}
