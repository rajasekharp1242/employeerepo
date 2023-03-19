package com.test.employeemodule.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.time.Period; 

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
		
		LocalDate currentDate = LocalDate.now();
		   
		for(Employee emp: employeeList )
		{
			taxAmt=0;
			TaxDeductionVO voObj=new TaxDeductionVO();
			voObj.setEmpId(emp.getId());
			voObj.setFirstName(emp.getFirstName());
			voObj.setLastName(emp.getLastName());
			 
			Long yearlySalary= emp.getSalary();
			Period difference = Period.between(emp.getDoj().toLocalDateTime().toLocalDate(), currentDate);
			if(difference.getYears()==0)
			{
				yearlySalary = (emp.getSalary()*difference.getMonths())%12;
			}
							
			if(yearlySalary.intValue()<=250000)
				taxAmt=0.0;
			else if(yearlySalary.intValue()>250000 && yearlySalary.intValue()<=500000)
				taxAmt=0.05*(yearlySalary.intValue()-250000);
			else if(yearlySalary.intValue()>500000 && yearlySalary.intValue()<=1000000)
				taxAmt=(0.1*(yearlySalary.intValue()-500000))+(0.05*250000);
			else
				taxAmt=(0.2*(yearlySalary.intValue()-1000000))+(0.1*500000)+(0.05*250000);
				
			voObj.setTaxAmount(taxAmt);
			
			cessAmt=0.0;
			if(yearlySalary>2500000)
			{
				cessAmt= 0.02*(yearlySalary.intValue()-2500000);
			}
			voObj.setCessAmount(cessAmt);
			empListResponse.add(voObj);
		}
		
		
		
		return empListResponse;
	}

}
