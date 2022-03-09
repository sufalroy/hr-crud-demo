package io.bitscope.example.hr.repository;

import io.bitscope.example.hr.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    List<Employee> findAll();

    Page<Employee> findAll(Pageable pageable);

    List<Employee> findByFirstNameContainingIgnoreCase(String firstName);

    List<Employee> findByFirstNameAndLastNameContainingIgnoreCase(String firstName, String lastName);
}
