package com.oocl.training.Repository;

import com.oocl.training.Entitiy.Employee;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaEmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> getEmployeesByGender(String gender);

//    List<Employee> findByCompanyId(Integer companyId);
}
