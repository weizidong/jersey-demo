package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wzd.model.entity.User;
import com.wzd.model.mapper.UserMapper;

import tk.mybatis.mapper.entity.Example;

/**
 * 
 * @author wzd
 *
 */
public class PageMapperTest extends BasicTest {

	// @Autowired
	// private CountryMapper countryMapper;

	@Autowired
	private SqlSession sqlSession;

	@Test
	public void test() {
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		Example example = new Example(User.class);
		example.createCriteria().andGreaterThan("id", 100);
		PageHelper.startPage(2, 10);
		List<User> countries = mapper.selectByExample(example);
		PageInfo<User> pageInfo = new PageInfo<User>(countries);
		System.out.println(pageInfo.getTotal());

		countries = mapper.selectByExample(example);
		pageInfo = new PageInfo<User>(countries);
		System.out.println(pageInfo.getTotal());
	}
}