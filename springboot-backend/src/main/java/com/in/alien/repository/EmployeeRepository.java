package com.in.alien.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.in.alien.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
