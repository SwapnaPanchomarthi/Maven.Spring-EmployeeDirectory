package io.zipcoder.persistenceapp;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeptRepo extends CrudRepository<Department, Integer> {
}
