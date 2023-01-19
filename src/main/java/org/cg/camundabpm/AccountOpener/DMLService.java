package org.cg.camundabpm.AccountOpener;

import java.util.Date;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

@Component
public class DMLService {

	@Autowired
	private AccountDao accountDao;

	public int saveAccountData(DelegateExecution execution) throws Exception {
		String name = (String) execution.getVariable("name");
		String address = (String) execution.getVariable("address");
		long pincode = (long) execution.getVariable("pincode");
		String pannum = (String) execution.getVariable("pannum");
		long adhaarnum = (long) execution.getVariable("adhaarnum");
		long age = (long) execution.getVariable("age");
		String email = (String) execution.getVariable("email");

		Account accountDetails = new Account();
		accountDetails.setName(name);
		accountDetails.setAddress(address);
		accountDetails.setPincode(pincode);
		accountDetails.setPannum(pannum);
		accountDetails.setAdhaarnum(adhaarnum);
		accountDetails.setAge(age);
		accountDetails.setEmail(email);

		Account account = accountDao.save(accountDetails);
		return accountDetails.getRequestID();
	}

	public int updateAccountData(DelegateExecution execution) throws Exception {
		int requestID = (int) execution.getVariable("requestID");
		String name = (String) execution.getVariable("name");
		String address = (String) execution.getVariable("address");
		long pincode = (long) execution.getVariable("pincode");
		String pannum = (String) execution.getVariable("pannum");
		long adhaarnum = (long) execution.getVariable("adhaarnum");
		long age = (long) execution.getVariable("age");
		String email = (String) execution.getVariable("email");
		long acnum = (long) execution.getVariable("acnum");

		Account accountDetails = accountDao.getById(requestID);
		accountDetails.setName(name);
		accountDetails.setAddress(address);
		accountDetails.setPincode(pincode);
		accountDetails.setPannum(pannum);
		accountDetails.setAdhaarnum(adhaarnum);
		accountDetails.setAge(age);
		accountDetails.setEmail(email);
		accountDetails.setAcnum(acnum);

		Account account = accountDao.save(accountDetails);
		return accountDetails.getRequestID();

	}

}
