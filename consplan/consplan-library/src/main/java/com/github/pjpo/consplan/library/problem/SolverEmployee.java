package com.github.pjpo.consplan.library.problem;

import com.github.pjpo.consplan.library.model.Employee;

/**
 * Employee defined for the solver with one choco indice
 * @author jp@dm.lan
 *
 */
public class SolverEmployee {
	
	private Employee employee;
	
	private Integer chocoIndice;

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Integer getChocoIndice() {
		return chocoIndice;
	}

	public void setChocoIndice(Integer chocoIndice) {
		this.chocoIndice = chocoIndice;
	}
	
}
