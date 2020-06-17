package ag.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ag.api.model.Employee;
import ag.api.service.interfaces.EmployeeService;
import ag.api.service.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository; 
	
	@Autowired
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public Employee saveEmployee(Employee employeeDetails) {
		return employeeRepository.save(employeeDetails); 
	}
	
	@Override
	public Employee addEmployee(Employee employeeDetails) {
		employeeDetails.setBowsEmployeeId(employeeDetails.getBowsEmployeeId());
		employeeDetails.setName(employeeDetails.getName());
		employeeDetails.setEmail(employeeDetails.getEmail());
		employeeDetails.setMobile(employeeDetails.getMobile());
		return saveEmployee(employeeDetails);
	}

	@Override
	public Employee getSingleEmployeeById(Integer id) {
		return null;
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public void deleteSingleEmployeeById(Integer id) {
		employeeRepository.deleteById(id);
	}

	@Override
	public void deleteAllEmployees() {
		employeeRepository.deleteAll();
	}

}
