package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.ApiResponse;
import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;



@RestController
@RequestMapping("/demo")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee emp) {
        return service.save(emp);
    }

    @GetMapping("/employees")
    public List<Employee> getUsers() {
        return service.getAll();
    }
//    @GetMapping("/employees/{id}")
//    public Optional<Employee> getUserById(@PathVariable String id) {
//    	int numericID= Integer.parseInt(id);
//		return service.getUserById(numericID);
//    }
    
//    @GetMapping("/employees/{id}")
//    public ResponseEntity<?> getEmployee(@PathVariable int id) {
//        return service.getUserById(id)
//                .<ResponseEntity<?>>map(employee -> ResponseEntity.ok(employee))
//                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("no id found"));
//    }
    @GetMapping("/employees/{id}")
    public ResponseEntity<ApiResponse<Employee>> getEmployee(@PathVariable int id) {
        Optional<Employee> emp = service.getUserById(id);
        if (emp.isPresent()) {
            return ResponseEntity.ok(new ApiResponse<>(true, "Success", emp.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(new ApiResponse<>(false, "no id found", null));
    }
}