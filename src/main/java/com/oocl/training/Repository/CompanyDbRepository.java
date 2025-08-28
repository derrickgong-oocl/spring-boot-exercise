package com.oocl.training.Repository;

import com.oocl.training.Entitiy.Company;
import com.oocl.training.Entitiy.Employee;

import java.util.List;
import java.util.Map;

public class CompanyDbRepository implements CompanyRepository {

    JpaCompanyRepository repository;

    @Override
    public List<Company> getCompanies() {
        return repository.findAll();
    }

    @Override
    public Company getCompanyById(Integer id) {
        return repository.getReferenceById(id);
    }

    @Override
    public List<Employee> getCompanyEmployees(Integer companyId) {
        Company company = repository.getReferenceById(companyId);
        return company.getEmployees();
    }

    @Override
    public void addCompany(Company company) {
        repository.save(company);
    }

    @Override
    public boolean updateCompanyName(Integer id, Map<String, String> request) {
        Company company = repository.getReferenceById(id);
        String name = request.get("name");
        company.setName(name);
        repository.save(company);
        return true;
    }

    @Override
    public boolean deleteCompany(Integer id) {
        repository.delete(getCompanyById(id));
        return true;
    }
}
