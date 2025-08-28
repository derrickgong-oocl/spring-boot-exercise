package com.oocl.training.Repository;

import com.oocl.training.Entitiy.Company;
import com.oocl.training.Entitiy.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCompanyRepository extends JpaRepository<Company, Integer> {
}
