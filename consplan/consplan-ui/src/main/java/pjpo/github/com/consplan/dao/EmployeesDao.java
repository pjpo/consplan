package pjpo.github.com.consplan.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import pjpo.github.com.consplan.model.Employee;

import com.google.common.collect.HashMultimap;

public class EmployeesDao {

	private HashMap<Long, Employee> employees;

	public EmployeesDao() {
		employees = new HashMap<>();
		Employee emp = new Employee();
		emp.setName("Nom1");
		emp.setPaidVacations(new LinkedList<>());
		emp.setRefusedPositions(new LinkedList<>());
		emp.setTimePart(100);
		emp.setUnpaidVacations(new LinkedList<>());
		emp.setWorkedVacs(HashMultimap.create());
		employees.put(0L, emp);
	}
	
	public Collection<Employee> getEmployees() {
		return employees.values();
	}

	public Employee getById(final Long employeeId) {
		return employees.get(employeeId);
	}
	
	public void deleteEmployee(final Long employeeId) {
		employees.remove(employeeId);
	}
}
