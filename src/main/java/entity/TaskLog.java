package entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name="TASKLOG")
public class TaskLog {

	@Id
	@GeneratedValue
	@Column (name = "Task_Id")
	private int taskId;
	
	@Column (name = "Start_Date")
	private Date startDate;
	
	@Column (name = "End_Date")
	private Date endDate;
	
	@Column (name = "Task")
	private String task;
	
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn (name = "Person_Id")
	private Person user;
		
	public TaskLog() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TaskLog(Date startDate, Date endDate, String task, Person user) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.task = task;
		this.user = user;
	}

	public int getId() {
		return taskId;
	}

	public void setId(int id) {
		this.taskId = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}
	
	
}
