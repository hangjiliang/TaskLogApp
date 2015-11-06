package databaseService;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import entity.Person;

@SuppressWarnings("deprecation")
public class MySqlDataBaseService {
	private SessionFactory _sessionFactory;
	
	public MySqlDataBaseService(){
		try{
			_sessionFactory = new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();
	      }catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
	}
	
	public SessionFactory getSessionFactory(){
		return _sessionFactory;
	}
	
	public void saveUser(Person user){
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
	
	public List<Person> getAllUsers(){
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
}
