package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repo;

    public Employee save(Employee emp) {
        return repo.save(emp);
    }

    public List<Employee> getAll() {
        return repo.findAll();
    }
public Optional<Employee>  getUserById(int numericID) {
	 Optional<Employee> optionalemployee = repo.findById(numericID);
	 List<Employee> list = new ArrayList<>();
	 
//		 list.add(optionalemployee.get());
		 return optionalemployee;
	 	 
}

public Optional<Employee> getUserByname(String name) {
	Optional<Employee> optionalemployeename = repo.findByName(name);
	return optionalemployeename;
}
}
