package ag.api.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ag.api.MembershipSystemApiApplication;
import ag.api.model.Employee;
import ag.api.model.EmployeeCard;
import ag.api.service.interfaces.EmployeeCardService;

@Component
public class DataLoader implements CommandLineRunner {

	private static final Logger LOG =  LoggerFactory.getLogger(MembershipSystemApiApplication.class);
	
	private EmployeeCardService cardService; 
	
	@Autowired
	public DataLoader(EmployeeCardService cardService) {
		this.cardService = cardService;
	}

	@Override
	public void run(String... args) throws Exception {
		
		// load some users 
		Employee employee1 = new Employee("121n", "Severus Snape", "severus@bows.com", "07123456789", "1234"); 
		LOG.info("Preloading: " + cardService.addEmployee(new EmployeeCard(employee1, "r345G7dqBy5wGO4L", 10.00, true)));
		
		Employee employee2 = new Employee("123n", "Albus Dumbledor", "professor@bows.com", "07784456488", "2345"); 
		LOG.info("Preloading: " + cardService.addEmployee(new EmployeeCard(employee2, "r567G7dqB765GO4L", 10.00, true)));
	}
	
	

}
