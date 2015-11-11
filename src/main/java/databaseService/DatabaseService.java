package databaseService;

import java.util.List;

import org.hibernate.SessionFactory;

import entity.Person;
import entity.TaskLog;

public interface DatabaseService {
	
	public abstract void savePerson(Person user);

	public abstract List<Person> getAllPerson();

	public abstract Person findPerson(String name);

	public abstract void saveTaskLog(TaskLog task);

	public abstract List<TaskLog> getAllTaskLogs();

	public abstract TaskLog findTaskLog(String task);

	public abstract void updateTaskLog(String personName, TaskLog task);

	public abstract void deletePerson(String personName);

	public abstract void deleteTaskLog(String task);

}