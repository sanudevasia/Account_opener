package org.cg.camundabpm.AccountOpener;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int requestID;
	
	private String name;
	private String address;
	private long pincode;
	private String pannum;
	private long adhaarnum;
	private long age;
	private long acnum;
	private String email;
	private String referenceno;
	
	
	public String getReferenceno() {
		return referenceno;
	}
	public void setReferenceno(String referenceno) {
		this.referenceno = referenceno;
	}
	public long getAcnum() {
		return acnum;
	}
	public void setAcnum(long acnum) {
		this.acnum = acnum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getAge() {
		return age;
	}
	public void setAge(long age) {
		this.age = age;
	}
	public int getRequestID() {
		return requestID;
	}
	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getPincode() {
		return pincode;
	}
	public void setPincode(long pincode) {
		this.pincode = pincode;
	}
	public String getPannum() {
		return pannum;
	}
	public void setPannum(String pannum) {
		this.pannum = pannum;
	}
	public long getAdhaarnum() {
		return adhaarnum;
	}
	public void setAdhaarnum(long adhaarnum) {
		this.adhaarnum = adhaarnum;
	}
	
	
	
	

}
