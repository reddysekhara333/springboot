package com.test.controller;

import com.test.model.Employee;
import com.test.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/emp")
public class EmpController {

    @Autowired
    private EmpService empService;


    @PostMapping("/save")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(empService.saveEmp(employee), HttpStatus.CREATED);
    }

    @GetMapping("/{empid}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Integer empid) {
        return ResponseEntity.ok(empService.getEmployee(empid));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getEmployees() {
        return ResponseEntity.ok(empService.getEmployees());
    }

    @GetMapping("/{offset}/{size}")
    public ResponseEntity<Page<Employee>> getEmployeePage(@PathVariable int offset, @PathVariable int size){
        return ResponseEntity.ok(empService.getPageEmployees(offset,size));
    }

    @GetMapping("/sort/{filed}")
    public ResponseEntity<List<Employee>> getEmployeesBySort(@PathVariable String filed){
        return ResponseEntity.ok(empService.getEmployeesBySortFiled(filed));
    }

    @DeleteMapping("{empid}")
    public String deleteById(@PathVariable Integer empid){
        empService.deleteById(empid);
        return "deleted Employee id is:-"+empid;

    }

    @PutMapping("/")
    public ResponseEntity<Employee> updateEmp(@RequestBody Employee employee){
        return new ResponseEntity<>(empService.updateEmp(employee),HttpStatus.ACCEPTED);
    }

}
