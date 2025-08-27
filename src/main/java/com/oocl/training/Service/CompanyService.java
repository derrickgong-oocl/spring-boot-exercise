package com.oocl.training.Service;

import com.oocl.training.Entitiy.Company;
import com.oocl.training.Entitiy.Employee;
import com.oocl.training.Entitiy.Page;
import com.oocl.training.Repository.CompanyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.*;

@Service
public class CompanyService {


    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getCompanies() {
        return companyRepository.getCompanies();
    }

    public Page<Company> getCompaniesByPages(Integer page, Integer size) {
        List<Company> companies = getCompanies();
        int totalCount = companies.size();
        int startIndex = (page - 1) * size;
        if (startIndex >= totalCount) {
            return new Page<>(page, size, totalCount, Collections.emptyList());
        }

        int endIndex = Math.min(startIndex + size, totalCount);
        List<Company> pageCompanies = new ArrayList<>(companies.subList(startIndex, endIndex));

        return new Page<>(page, size, totalCount, pageCompanies);
    }

    public Company getCompanyById(Integer id){
        return companyRepository.getCompanyById(id);
    }

    public List<Employee> getCompanyEmployees(Integer companyId){
        return companyRepository.getCompanyEmployees(companyId);
    }

    public void addCompany(Company company){
        companyRepository.addCompany(company);
    }

    public boolean updateCompanyName(Integer id, Map<String, String> request) {
        return companyRepository.updateCompanyName(id, request);
    }

    public boolean deleteCompany(Integer id) {
        return companyRepository.deleteCompany(id);
    }


}
