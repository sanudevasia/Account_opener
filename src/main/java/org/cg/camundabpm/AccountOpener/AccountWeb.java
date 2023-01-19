package org.cg.camundabpm.AccountOpener;

import java.util.HashMap;
import java.util.List;

import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.impl.HistoryServiceImpl;
import org.camunda.bpm.engine.rest.impl.history.HistoryRestServiceImpl;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
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

		// return new ResponseEntity<>(taskService.createTaskQuery().active().list(),
		// HttpStatus.OK);
	}

	@PutMapping("/updateTaskStatus")
	public void updateTaskStatus(@RequestBody TaskStatus taskStatus) {
		// get the task id and task name,
		// use the process engine adn runtime service to update the task status
		// check what kind of task it is from the task status object,
		// if it is manager task then add one variable to the task variable as
		// "isManagerApproved"
		// also you can get the process instance and process scope using the task id and
		// set the variable at
		// process level.
		if (taskStatus.getTaskName().equalsIgnoreCase("manager")) {
			if (taskStatus.getTaskStatus().equalsIgnoreCase("approved")) {
				// get the process instance scope from the task id and then set the variable at
				// process scope
			} else if (taskStatus.getTaskStatus().equalsIgnoreCase("rejected")) {
				// get the process instance scope and set the variable isManagerApproved==false;
			}
		}
	}

}
