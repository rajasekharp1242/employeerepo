package com.test.employeemodule.entity;

public class TaxDeductionVO {
  private Long empId;
  private String firstName;
  private String lastName;
  private Long salary;
  private Double taxAmount;
  private Double cessAmount;
  
public Long getEmpId() {
	return empId;
}
public void setEmpId(Long empId) {
	this.empId = empId;
}
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public Long getSalary() {
	return salary;
}
public void setSalary(Long salary) {
	this.salary = salary;
}
public Double getTaxAmount() {
	return taxAmount;
}
public void setTaxAmount(Double taxAmount) {
	this.taxAmount = taxAmount;
}
public Double getCessAmount() {
	return cessAmount;
}
public void setCessAmount(Double cessAmount) {
	this.cessAmount = cessAmount;
}
  
  
  
}
