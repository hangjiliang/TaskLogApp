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
@Table (name="USER")
public class User {
	@Id
	@GeneratedValue
	@Column (name = "Id")
	private int id;
	
	@Column (name = "name")
	private String name;
	
	@OneToMany (fetch=FetchType.LAZY, mappedBy = "compound", cascade = CascadeType.ALL)
	private List<TaskLog> taskLogs = new ArrayList<TaskLog>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	

}
