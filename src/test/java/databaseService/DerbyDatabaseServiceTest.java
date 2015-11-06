package databaseService;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import entity.Person;
import entity.TaskLog;

public class DerbyDatabaseServiceTest {
	DerbyDatabaseService service = null;
	
	@Before
	public void setUp() throws Exception {
		service = new DerbyDatabaseService();
	}
	
	@Test
	public void testAddandDelete() throws Exception{
		Person u1 = new Person("u1", "u1");
		Person u2 = new Person("u2", "u1");
		Person u3 = new Person("u3", "u1");
		
		service.savePerson(u1);
		service.savePerson(u2);
		service.savePerson(u3);
		
		assertEquals(3, service.getAllPerson().size());
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
		Date date1 = sdf.parse("31-08-2015");
		Date date2 = sdf.parse("20-09-2015");
		
		TaskLog t1 = new TaskLog(date1, date2, "job1", u1);
		TaskLog t2 = new TaskLog(date1, date2, "job2", u1);
		service.saveTaskLog(t1);
		service.saveTaskLog(t2);
		assertEquals(2, service.getAllTaskLogs().size());
		
		Person u = service.findPerson(u1.getName());
		assertEquals(2, u.getTaskLogs().size());
		
		service.updateTaskLog(u1.getName(), new TaskLog(date1, date2, "job3", null));
		u = service.findPerson(u1.getName());
		assertEquals(3, u.getTaskLogs().size());
		assertEquals(3, service.getAllTaskLogs().size());
		
		TaskLog t3 = new TaskLog(date1, date2, "job3", u2);
		service.saveTaskLog(t3);
		assertEquals(4, service.getAllTaskLogs().size());
		
		service.deletePerson(u1.getName());
		assertEquals(2, service.getAllPerson().size());
		assertEquals(1, service.getAllTaskLogs().size());
		
		service.deleteTaskLog("job3");
		assertEquals(0, service.getAllTaskLogs().size());
	}
}
