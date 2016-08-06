package library;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lauguobin.www.dao.BookDao;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration({"classpath*:applicationContext*.xml"}) 
public class TestDao
{
	@Autowired
	private BookDao b;
	
	@Test
	public void testDao()
	{
		b.getExistBooks();
	}
}
