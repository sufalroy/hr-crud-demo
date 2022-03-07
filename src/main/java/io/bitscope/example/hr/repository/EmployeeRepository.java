package io.bitscope.example.hr.repository;

import io.bitscope.example.hr.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    List<Employee> findByFirstName(String firstName);

    List<Employee> findByFirstNameAndLastName(String firstName, String lastName);
}
