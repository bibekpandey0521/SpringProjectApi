package com.bway.springproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bway.springproject.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
