package com.javainuse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.javainuse.exceptionhandling.ResourceNotFoundException;
import com.javainuse.model.Employee;
import com.javainuse.service.EmployeeService;
import com.javainuse.service.EmployeeServiceException;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	public Employee getEmpl() throws ResourceNotFoundException, EmployeeServiceException {

		Employee emp = employeeService.getEmployee();

		if (emp == null) {
			throw new ResourceNotFoundException("Employee not found");
		}
		return emp;
	}

	@RequestMapping(value = "/employee2", method = RequestMethod.GET)
	public ResponseEntity<Object>  getEmp2() throws  Throwable{
		Employee emp=null;
		try {
        
			System.out.println("calling service foe getting null");
		emp = employeeService.getEmployeeNull();
		System.out.println("called serivei");
		if (emp==null) {
			System.out.println("inside emp==null true condition");
			throw new ResourceNotFoundException("Employee not found");
		}
		     }
		
	    	catch(ResourceNotFoundException e) {
		     //throw new ResourceNotFoundException(e.getMessage());
			}
		
		    catch(Exception e) {
			throw new Exception("throwing generic exeception and not going to execute this");
		}
		
		System.out.println("control came here een aftwer throw");
		return ResponseEntity.ok(emp);
	}
	
	/** 
	 * GOLDEN RULE: if you throw a exception and do not catch then the exc will be propogated to calling methods and so on and finally passed to browser UI.
	 * if you have caught it, then rethorow the same , else once catched its like neutralized and will not propogate further unless rethrown
	 * 
	 * 
	 * In the above method, If I am receiving any csutom exeception from called method of service, or throwinf new custom exeception 
	//1. if you have received any exception from called method emp = employeeService.getEmployeeNull(); then catch it with execption name and throw it, then only this execeptin
	will be forwared to client, if its not handled in catch and ret thrown then generic execptiob catch block will execute.
	any time any one catch will be executed, after one catch is executed you have to re throe it, else since you catched it will propgate to client.
	SO CATCH AND TROW SO u can see it in browser.

	YOU CAN PLACE THE RETURN PART AT LAST , when u get error bcz of try that this mehtod shud return bla bla, bcs if any execption happens dont worring  , the catch blokc
	will execute and ur return statement placed at last will not exeuited.
	
	If you thown ResourceNotFoundException from try block, so for try block we will have  general catch execption any ways. So afte throwing ResourceNotFoundException
	from try if you are not catching ResourceNotFoundException and re throwing it, then ur general exceptinon catch block will get executed
	
	**/

	@RequestMapping(value = "/employee3", method = RequestMethod.GET)
	public ResponseEntity<Object> getEmp3() throws Throwable {
		Employee emp=null;
		try {
			 emp = employeeService.getEmployeeException();
			if (emp == null) {
				throw new ResourceNotFoundException("Employee not found");
			}
			 
		} 
		
		catch(Exception e) {
			
			throw new Exception("Internal server Error");
		}	
		return ResponseEntity.ok(emp);
	} 
}
