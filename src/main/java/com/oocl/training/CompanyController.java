package com.oocl.training;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

// post http://localhost:8080/api/v1/companies

@RestController
@RequestMapping("/api/v1/companies")

public class CompanyController {
    private final Map<Integer, Company> companyDB = new HashMap<>();


    @GetMapping("/page")
    public Page<Company> getCompaniesByPages(@RequestParam int page, @RequestParam int size) {
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

    @GetMapping
    public List<Company> getCompanies() {
        return new ArrayList<>(companyDB.values());
    }

    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable Integer id){
        return companyDB.get(id);
    }

    @GetMapping("/{companyId}/employees")
    public List<Employee> getCompanyEmployees(@PathVariable Integer companyId){
        Company company = companyDB.get(companyId);
        if(company != null){
            return company.getEmployees();
        }
        return new ArrayList<>();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addCompany(@RequestBody Company company){
        company.setId(companyDB.size() + 1);
        companyDB.put(companyDB.size() + 1, company);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCompanyName(@PathVariable Integer id, @RequestBody Map<String, String> request) {
        Company existingCompany = companyDB.get(id);
        if (existingCompany != null) {
            existingCompany.setName(request.get("name"));
            companyDB.put(id, existingCompany);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity deleteCompany(@PathVariable Integer id) {
        if (companyDB.containsKey(id)) {
            companyDB.remove(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
