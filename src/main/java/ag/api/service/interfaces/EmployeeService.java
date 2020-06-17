package ag.api.service.interfaces;

import java.util.List;

import ag.api.model.Employee;

public interface EmployeeService {
	
	Employee addEmployee(Employee employeeDetails); 
	
	Employee getSingleEmployeeById(Integer id); 
	List<Employee> getAllEmployees(); 
	
	void deleteSingleEmployeeById(Integer id); 
	void deleteAllEmployees(); 
}
