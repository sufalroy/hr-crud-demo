package io.bitscope.example.hr.repository;

import io.bitscope.example.hr.model.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long> {

    List<Department> findByDepartmentName(String departmentName);
}
