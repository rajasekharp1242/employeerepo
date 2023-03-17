package com.test.employeemodule.service;

import java.util.List;

import com.test.employeemodule.entity.Employee;
import com.test.employeemodule.entity.TaxDeductionVO;

public interface EmployeeService {

	Employee createEmployee(Employee emp);
	
	List<TaxDeductionVO> getSalaryWithTaxDeduction();
}
