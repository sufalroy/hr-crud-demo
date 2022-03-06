package io.bitscope.example.hr;

import io.bitscope.example.hr.repositories.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class HrCrudDemoApplication {

	private Logger logger = LoggerFactory.getLogger(HrCrudDemoApplication.class);

	@Autowired
	private EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(HrCrudDemoApplication.class, args);
	}

	@PostConstruct
	public void start() {
		List<Long> ids = new ArrayList<>();
		ids.add(101L);
		ids.add(102L);

		employeeRepository.findAllById(ids).forEach(employee -> logger.info(employee.toString()));
	}
}
