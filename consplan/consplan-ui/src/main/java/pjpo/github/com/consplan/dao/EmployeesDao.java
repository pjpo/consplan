package pjpo.github.com.consplan.dao;

import java.util.LinkedList;
import java.util.List;

import com.github.pjpo.consplan.library.model.Employee;
import com.google.common.collect.HashMultimap;

public class EmployeesDao {

	public List<Employee> getEmployees() {
		LinkedList<Employee> test = new LinkedList<>();
		Employee emp = new Employee();
		emp.setName("Nom1");
		emp.setPaidVacations(new LinkedList<>());
		emp.setRefusedPositions(new LinkedList<>());
		emp.setTimePart(100);
		emp.setUnpaidVacations(new LinkedList<>());
		emp.setWorkedVacs(HashMultimap.create());
		test.add(emp);
		return test;
	}
	
}
