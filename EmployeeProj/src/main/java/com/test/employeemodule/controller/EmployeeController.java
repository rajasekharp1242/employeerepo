package com.test.employeemodule.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.employeemodule.entity.Employee;
import com.test.employeemodule.entity.TaxDeductionVO;
import com.test.employeemodule.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping
	public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee emp)
	{
		Employee savedEmployee = employeeService.createEmployee(emp);
		return new ResponseEntity<Employee>(savedEmployee,HttpStatus.CREATED);
	}
	
	
	@GetMapping
	public ResponseEntity<List<TaxDeductionVO>> getAllEmployeesWithTaxDeduct()
	{
		List<TaxDeductionVO> employeesList = employeeService.getSalaryWithTaxDeduction();
		return new ResponseEntity<List<TaxDeductionVO>>(employeesList, HttpStatus.OK);
	}
}
