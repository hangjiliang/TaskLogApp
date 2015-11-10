package databaseService;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Restrictions;

import entity.Person;
import entity.TaskLog;

@SuppressWarnings("deprecation")
public class DerbyDatabaseService implements DatabaseService {
	private SessionFactory _sessionFactory;
	
	public DerbyDatabaseService(){
		try{
			_sessionFactory = new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();
	      }catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
	}
	
	/* (non-Javadoc)
	 * @see databaseService.DatabaseService#getSessionFactory()
	 */
	public SessionFactory getSessionFactory(){
		return _sessionFactory;
	}
	
	/* (non-Javadoc)
	 * @see databaseService.DatabaseService#savePerson(entity.Person)
	 */
	public void savePerson(Person user){
		Session session = _sessionFactory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			session.save(user);
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.clear();
		}
	}
	
	/* (non-Javadoc)
	 * @see databaseService.DatabaseService#getAllPerson()
	 */
	public List<Person> getAllPerson(){
		Session session = _sessionFactory.openSession();
		Transaction tx = null;
		List users = null;
		try{
			tx = session.beginTransaction();
			users = session.createQuery("FROM Person").list();
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.clear();
		}
		return users;
	}
	
	/* (non-Javadoc)
	 * @see databaseService.DatabaseService#findPerson(java.lang.String)
	 */
	public Person findPerson(String name){
		Session session = _sessionFactory.openSession();
		Transaction tx = null;
		Person person = null;
		try{
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Person.class);
			person = (Person) criteria.add(Restrictions.eq("name", name)).uniqueResult();
			Hibernate.initialize(person.getTaskLogs());
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.clear();
		}
		return person;

	}
	
	/* (non-Javadoc)
	 * @see databaseService.DatabaseService#saveTaskLog(entity.TaskLog)
	 */
	public void saveTaskLog(TaskLog task){
		Session session = _sessionFactory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			session.save(task);
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.clear();
		}
	}
	
	/* (non-Javadoc)
	 * @see databaseService.DatabaseService#getAllTaskLogs()
	 */
	public List<TaskLog> getAllTaskLogs(){
		Session session = _sessionFactory.openSession();
		Transaction tx = null;
		List tasks = null;
		try{
			tx = session.beginTransaction();
			tasks = session.createQuery("FROM TaskLog").list();
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.clear();
		}
		return tasks;
	}
	
	/* (non-Javadoc)
	 * @see databaseService.DatabaseService#findTaskLog(java.lang.String)
	 */
	public TaskLog findTaskLog(String task){
		Session session = _sessionFactory.openSession();
		Transaction tx = null;
		TaskLog t = null;
		try{
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(TaskLog.class);
			t = (TaskLog) criteria.add(Restrictions.eq("task", task)).uniqueResult();
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.clear();
		}
		return t;
	}
	
	/* (non-Javadoc)
	 * @see databaseService.DatabaseService#updateTaskLog(java.lang.String, entity.TaskLog)
	 */
	public void updateTaskLog(String personName, TaskLog task){
		Person p = this.findPerson(personName);
		if(p != null){
			TaskLog t = this.findTaskLog(task.getTask());
			Session session = _sessionFactory.openSession();
			Transaction tx = null;
			try{
				tx = session.beginTransaction();
				session.update(p);
				if(t != null){
					session.update(t);
					t.setEndDate(task.getEndDate());
					t.setStartDate(task.getStartDate());
					t.setTask(task.getTask());
				}else{
					task.setPerson(p);
					session.save(task);
				}
				tx.commit();
			}catch(HibernateException e){
				e.printStackTrace();
			}finally{
				session.clear();
			}
			
		}
		
	}
	
	/* (non-Javadoc)
	 * @see databaseService.DatabaseService#deletePerson(java.lang.String)
	 */
	public void deletePerson(String personName){
		Session session = _sessionFactory.openSession();
		Transaction tx = null;
		Person p = this.findPerson(personName);
		if(p != null){
			try{
				tx = session.beginTransaction();
				session.update(p);
				session.delete(p);
				tx.commit();
			}catch(HibernateException e){
				e.printStackTrace();
			}finally{
				session.clear();
			}
		}

	}	
	
	/* (non-Javadoc)
	 * @see databaseService.DatabaseService#deleteTaskLog(java.lang.String)
	 */
	public void deleteTaskLog(String task){
		Session session = _sessionFactory.openSession();
		Transaction tx = null;
		TaskLog t = this.findTaskLog(task);
		if(t != null){
			try{
				tx = session.beginTransaction();
				session.update(t);
				session.delete(t);
				tx.commit();
			}catch(HibernateException e){
				e.printStackTrace();
			}finally{
				session.clear();
			}
		}
	}
	
}
