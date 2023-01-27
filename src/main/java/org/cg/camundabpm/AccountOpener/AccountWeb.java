package org.cg.camundabpm.AccountOpener;

import java.util.HashMap;
import java.util.List;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.camunda.bpm.engine.task.Task;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/account")
public class AccountWeb {

	@Autowired
	RuntimeService runtimeService;
	@Autowired
	ProcessEngine processEngine;
	@Autowired
	TaskService taskService;
	
	@Autowired
	GlobalExceptionHandler globalExceptionHandler;
	
	@Autowired
	private DMLService service;

	@PostMapping("/new-account")
	public ResponseEntity<Account> startAccountOpeningJourney(@RequestBody Account newAccountOp) {

		HashMap<String, Object> newAccountMap = new HashMap<>();
		newAccountMap.put("name", newAccountOp.getName());
		newAccountMap.put("address", newAccountOp.getAddress());
		newAccountMap.put("pincode", newAccountOp.getPincode());
		newAccountMap.put("pannum", newAccountOp.getPannum());
		newAccountMap.put("adhaarnum", newAccountOp.getAdhaarnum());
		newAccountMap.put("age", newAccountOp.getAge());
		newAccountMap.put("email", newAccountOp.getEmail());

		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("AccountOpener", newAccountMap);
		if (null != processInstance) {
			newAccountOp.setReferenceno(processInstance.getProcessInstanceId());
			return new ResponseEntity<>(newAccountOp, HttpStatus.OK);
		}
		return new ResponseEntity<>(newAccountOp, HttpStatus.OK);

	}

	@GetMapping("/tasks")
	public ResponseEntity<List<Task>> getTasks() {
		System.out.println(processEngine.getTaskService().createTaskQuery().active().list().size());
		return new ResponseEntity<>(processEngine.getTaskService().createTaskQuery().active().list(), HttpStatus.OK);

	}

	@PutMapping("/updateTaskStatus")
	public ResponseEntity<TaskStatus> updateTaskStatus(@RequestBody TaskStatus taskStatus) {

		
			if (taskStatus.getTaskName().equalsIgnoreCase("manager")) {
				if (taskStatus.getTaskStatus().equalsIgnoreCase("approved")) {
					taskStatus.setManagerIsApproved(true);
					service.approved(taskStatus.isManagerIsApproved());
				} else if (taskStatus.getTaskStatus().equalsIgnoreCase("rejected")) {
					taskStatus.setManagerIsApproved(false);
					service.approved(taskStatus.isManagerIsApproved());
				}
				taskService.complete(taskStatus.getTaskId());

			} else if (taskStatus.getTaskName().equalsIgnoreCase("front desk to update customer")) {

				if (taskStatus.getTaskStatus().equalsIgnoreCase("approved")) {
					taskStatus.setManagerIsApproved(true);
					taskService.setVariable(taskStatus.getTaskId(), "variableUpdated",
							taskStatus.isManagerIsApproved());
				} else if (taskStatus.getTaskStatus().equalsIgnoreCase("rejected")) {
					taskStatus.setManagerIsApproved(false);
					taskService.setVariable(taskStatus.getTaskId(), "variableUpdated",
							taskStatus.isManagerIsApproved());
				}
				taskService.complete(taskStatus.getTaskId());
			}
			
			return new ResponseEntity<>(taskStatus, HttpStatus.OK);
		
		

	}

}
