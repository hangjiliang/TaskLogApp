package entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name="PERSON")
public class Person {
	@Id
	@GeneratedValue
	@Column (name = "Person_Id")
	private int userId;
	
	@Column (name = "Name", unique=true)
	private String name;
	
	@Column (name = "Password")
	private String password;
	
	@OneToMany (fetch=FetchType.LAZY, mappedBy = "person", cascade = CascadeType.ALL)
	private List<TaskLog> taskLogs = new ArrayList<TaskLog>();
	
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Person(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}

	public int getId() {
		return userId;
	}

	public void setId(int id) {
		this.userId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TaskLog> getTaskLogs() {
		return taskLogs;
	}

	public void setTaskLogs(List<TaskLog> taskLogs) {
		this.taskLogs = taskLogs;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

}
