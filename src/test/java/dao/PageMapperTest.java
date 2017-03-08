package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.isea533.mybatis.mapper.CountryMapper;
import com.isea533.mybatis.model.Country;

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
		CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
		Example example = new Example(Country.class);
		example.createCriteria().andGreaterThan("id", 100);
		PageHelper.startPage(2, 10);
		List<Country> countries = countryMapper.selectByExample(example);
		PageInfo<Country> pageInfo = new PageInfo<Country>(countries);
		System.out.println(pageInfo.getTotal());

		countries = countryMapper.selectByExample(example);
		pageInfo = new PageInfo<Country>(countries);
		System.out.println(pageInfo.getTotal());
	}
}
