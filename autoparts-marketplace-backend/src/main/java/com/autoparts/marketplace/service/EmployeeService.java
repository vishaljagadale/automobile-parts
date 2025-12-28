package com.autoparts.marketplace.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autoparts.marketplace.entity.Employee;
import com.autoparts.marketplace.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	public List<Employee> getAllEmployee() {
		return employeeRepository.findAll();
	}

	public Optional<Employee> getEmployeeById(Long id) {
		return employeeRepository.findById(id);
	}

	public String deleteEmployeeById(Long id) {
		employeeRepository.deleteById(id);
		return "Employee id : " + id + " is deleted successfully";
	}

	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public Optional<Employee> updateEmployeeById(Long id, Employee employee) {
		return employeeRepository.findById(id).map(exist -> {
			exist.setDepartment(employee.getDepartment());
			exist.setName(employee.getName());
			exist.setSalary(employee.getSalary());
			return employeeRepository.save(exist);
		});
	}
}
