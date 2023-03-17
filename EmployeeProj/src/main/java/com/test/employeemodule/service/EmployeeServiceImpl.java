package com.test.employeemodule.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.employeemodule.entity.Employee;
import com.test.employeemodule.entity.TaxDeductionVO;
import com.test.employeemodule.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee createEmployee(Employee emp) {
		
		return employeeRepository.save(emp);
		
	}

	@Override
	public List<TaxDeductionVO> getSalaryWithTaxDeduction() {
		
		List<Employee> employeeList = employeeRepository.findAll();
		List<TaxDeductionVO> empListResponse = new ArrayList<TaxDeductionVO>();
		double taxAmt;
		double cessAmt;
		for(Employee emp: employeeList )
		{
			taxAmt=0;
			TaxDeductionVO voObj=new TaxDeductionVO();
			voObj.setEmpId(emp.getId());
			voObj.setFirstName(emp.getFirstName());
			voObj.setLastName(emp.getLastName());
	
							
			if(emp.getSalary().intValue()<=250000)
				taxAmt=0.0;
			else if(emp.getSalary().intValue()>250000 && emp.getSalary().intValue()<=500000)
				taxAmt=0.05*(emp.getSalary().intValue()-250000);
			else if(emp.getSalary().intValue()>500000 && emp.getSalary().intValue()<=1000000)
				taxAmt=(0.1*(emp.getSalary().intValue()-500000))+(0.05*250000);
			else
				taxAmt=(0.2*(emp.getSalary().intValue()-1000000))+(0.2*500000)+(0.1*250000)+(0.05*250000);
			
			voObj.setTaxAmount(taxAmt);
			
			cessAmt=0.0;
			if(emp.getSalary()>2500000)
			{
				cessAmt= 0.02*(emp.getSalary().intValue()-2500000);
			}
			voObj.setCessAmount(cessAmt);
			empListResponse.add(voObj);
		}
		
		
		
		return empListResponse;
	}

}
