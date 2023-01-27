package org.cg.camundabpm.AccountOpener;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TaskStatus {
	
	@Id
	private String taskId;
	private String taskName;
	private String taskStatus;
	private boolean managerIsApproved;

	public boolean isManagerIsApproved() {
		return managerIsApproved;
	}

	public void setManagerIsApproved(boolean managerIsApproved) {
		this.managerIsApproved = managerIsApproved;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

}
