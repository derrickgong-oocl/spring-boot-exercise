package com.oocl.training.Repository;

import com.oocl.training.Entitiy.Company;
import com.oocl.training.Entitiy.Employee;
import com.oocl.training.Entitiy.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CompanyMemoryRepository implements CompanyRepository{
    public CompanyMemoryRepository() {

    }
    private Integer companyTotal = 5;

    private final HashMap<Integer, Company> companyDB = new HashMap<>(Map.of(
            1, new Company(1, "Acme Corporation", List.of(
                    new Employee(1, "John Smith", 32, "Male", 5000.0, true),
                    new Employee(2, "Jane Johnson", 28, "Female", 6000.0, true)
            )),
            2, new Company(2, "TechCom Solutions", List.of(
                    new Employee(3, "David Williams", 35, "Male", 5500.0, true),
                    new Employee(4, "Emily Brown", 23, "Female", 4500.0, true),
                    new Employee(5, "Michael Jones", 40, "Male", 7000.0, true)
            )),
            3, new Company(3, "Global Innovators", List.of()),
            4, new Company(4, "Stellar Enterprises", List.of()),
            5, new Company(5, "Nexus Industries", List.of())
    ));

    @Override
    public List<Company> getCompanies() {
        return new ArrayList<>(companyDB.values());
    }


    @Override
    public Company getCompanyById(Integer id){
        return companyDB.get(id);
    }

    @Override
    public List<Employee> getCompanyEmployees(Integer companyId){
        Company company = companyDB.get(companyId);
        if(company != null){
            return company.getEmployees();
        }
        return new ArrayList<>();
    }

    @Override
    public void addCompany(Company company){
        companyTotal += 1;
        company.setId(companyTotal);
        companyDB.put(companyTotal, company);
    }

    @Override
    public boolean updateCompanyName(Integer id, Map<String, String> request) {
        Company existingCompany = companyDB.get(id);
        if (existingCompany != null) {
            existingCompany.setName(request.get("name"));
            companyDB.put(id, existingCompany);
            return true;
        } else {
            return false;

        }
    }

    @Override
    public boolean deleteCompany(Integer id) {
        if (companyDB.containsKey(id)) {
            companyDB.remove(id);
            return true;
        } else {
            return false;
        }
    }

}
