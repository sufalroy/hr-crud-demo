package io.bitscope.example.hr;

import io.bitscope.example.hr.model.Department;
import io.bitscope.example.hr.model.Employee;
import io.bitscope.example.hr.repository.DepartmentRepository;
import io.bitscope.example.hr.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
public class TestQueryMethod {

    private Logger logger = LoggerFactory.getLogger(TestQueryMethod.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    @Transactional
    public void testFindByFirstName() {
        logger.info(".... testFindByFirstName ....");

        List<Employee> emp = employeeRepository.findByFirstName("Patrick");
        Assertions.assertNotNull(emp);
        Assertions.assertFalse(emp.isEmpty());
        logger.info("findByFirstName result: " + emp.get(0).toString());
    }

    @Test
    @Transactional
    public void testFindByFirstNameAndLastName() {
        logger.info(".... testFindByFirstNameAndLastName ....");

        List<Employee> emp = employeeRepository.findByFirstNameAndLastName("Adam", "Fripp");
        Assertions.assertNotNull(emp);
        Assertions.assertFalse(emp.isEmpty());
        logger.info("findByFirstNameAndLastName result: " + emp.get(0).toString());
    }

    @Test
    @Transactional
    public void testFindByDepartmentName() {
        logger.info(".... testFindByDepartmentName ....");

        List<Department> dep = departmentRepository.findByDepartmentName("IT Support");
        Assertions.assertNotNull(dep);
        Assertions.assertFalse(dep.isEmpty());
        logger.info("findByDepartmentName result: " + dep.get(0).toString());
    }
}
