package com.test.service;

import com.test.model.Employee;
import com.test.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpService {

    @Autowired
    private EmployeeRepo employeeRepo;


    public Employee saveEmp(Employee employee){
        return  employeeRepo.save(employee);
    }

    public Employee getEmployee(Integer id) {
        return employeeRepo.getByEmpId(id);
    }

    public List<Employee> getEmployees(){
        return employeeRepo.findAll();
    }

    public Page<Employee> getPageEmployees(int offset, int pageSize){
        Page<Employee> emp=employeeRepo.findAll(PageRequest.of(offset,pageSize));
        return emp;
    }

    public List<Employee> getEmployeesBySortFiled(String field){
        return employeeRepo.findAll(Sort.by(Sort.Direction.ASC,field));
    }

    public String deleteById(Integer empid) {
        employeeRepo.deleteById(empid);
        return "deleted.";
    }

    public Employee updateEmp(Employee employee) {
        Employee employee1=employeeRepo.getByEmpId(employee.getEmpId());
        if(employee1!=null){
            employee1.setEmpName(employee.getEmpName());
            employee1.setSalary(employee.getSalary());
            employee1.setEmail(employee.getEmail());

        }else{
            return null;
        }

        return employeeRepo.save(employee1);
    }
}
