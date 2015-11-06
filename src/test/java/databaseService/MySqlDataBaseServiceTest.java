package databaseService;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import entity.Person;

public class MySqlDataBaseServiceTest {
	MySqlDataBaseService service = null;
	
	@Before
	public void setUp() throws Exception {
		service = new MySqlDataBaseService();
	}
	
	@Test
	public void test(){
		Person u1 = new Person("u1", "u1");
		Person u2 = new Person("u2", "u1");
		Person u3 = new Person("u3", "u1");
		
		service.saveUser(u1);
		service.saveUser(u2);
		service.saveUser(u3);
		
		assertEquals(3, service.getAllUsers().size());
	}
}
