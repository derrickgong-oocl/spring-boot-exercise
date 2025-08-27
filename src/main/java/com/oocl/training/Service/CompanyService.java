package com.oocl.training.Service;

import com.oocl.training.Entitiy.Company;
import com.oocl.training.Entitiy.Employee;
import com.oocl.training.Entitiy.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.*;

@Service
public class CompanyService {

    private final HashMap<Integer, Company> companyDB = new HashMap<>(Map.of(
            1, new Company(1, "Acme Corporation", List.of(
                    new Employee(1, "John Smith", 32, "Male", 5000.0),
                    new Employee(2, "Jane Johnson", 28, "Female", 6000.0)
            )),
            2, new Company(2, "TechCom Solutions", List.of(
                    new Employee(3, "David Williams", 35, "Male", 5500.0),
                    new Employee(4, "Emily Brown", 23, "Female", 4500.0),
                    new Employee(5, "Michael Jones", 40, "Male", 7000.0)
            )),
            3, new Company(3, "Global Innovators", List.of()),
            4, new Company(4, "Stellar Enterprises", List.of()),
            5, new Company(5, "Nexus Industries", List.of())
    ));

    public List<Company> getCompanies() {
        return new ArrayList<>(companyDB.values());
    }

    public Page<Company> getCompaniesByPages(Integer page, Integer size) {
        List<Company> companies = new ArrayList<>(companyDB.values());
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
        return companyDB.get(id);
    }

    public List<Employee> getCompanyEmployees(Integer companyId){
        Company company = companyDB.get(companyId);
        if(company != null){
            return company.getEmployees();
        }
        return new ArrayList<>();
    }

    public void addCompany(Company company){
        company.setId(companyDB.size() + 1);
        companyDB.put(companyDB.size() + 1, company);
    }

    public ResponseEntity updateCompanyName(Integer id, Map<String, String> request) {
        Company existingCompany = companyDB.get(id);
        if (existingCompany != null) {
            existingCompany.setName(request.get("name"));
            companyDB.put(id, existingCompany);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity deleteCompany(Integer id) {
        if (companyDB.containsKey(id)) {
            companyDB.remove(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
