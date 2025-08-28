package com.oocl.training.Repository;

import com.oocl.training.Entitiy.Company;
import com.oocl.training.Entitiy.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface CompanyRepository {

    List<Company> getCompanies();

    Company getCompanyById(Integer id);

    List<Employee> getCompanyEmployees(Integer companyId);

    void addCompany(Company company);

    boolean updateCompanyName(Integer id, Map<String, String> request);

    boolean deleteCompany(Integer id);
}
