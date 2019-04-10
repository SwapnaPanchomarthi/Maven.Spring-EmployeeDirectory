package io.zipcoder.persistenceapp;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntityRepo extends CrudRepository<Employee, Integer> {
    List<Employee> findByManager(Integer manager);
    List<Employee> findByDepartment(Department department);
}
