package com.github.pjpo.consplan.library.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.github.pjpo.consplan.library.utils.IntervalDateTime;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

/**
 * Bean for Worker definition
 * @author jp@dm.lan
 *
 */
public class Employee implements Cloneable {

	/** Worker's name */
	private String name = null;
	
	/** Part time (can be of any scale) */
	private Integer timePart = null;
	
	/** List of paid vacations */
	private List<IntervalDateTime> paidVacations = null;
	
	/** List of unpaid vacations */
	private List<IntervalDateTime> unpaidVacations = null;

	/** List of predefined worked positions */
	private Multimap<LocalDate, String> workedPositions = null;
	
	/** List of positions that this worker can not fill */
	private List<String> refusedPositions = null;
	
	/** Internal indice for this worker (used in choco solver) */
	private Integer internalIndice = null;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Integer getTimePart() {
		return timePart;
	}

	public void setTimePart(final Integer timePart) {
		this.timePart = timePart;
	}

	public List<IntervalDateTime> getPaidVacations() {
		return paidVacations;
	}

	public void setPaidVacations(final List<IntervalDateTime> paidVacations) {
		this.paidVacations = paidVacations;
	}

	public List<IntervalDateTime> getUnpaidVacations() {
		return unpaidVacations;
	}

	public void setUnpaidVacations(final List<IntervalDateTime> unpaidVacations) {
		this.unpaidVacations = unpaidVacations;
	}

	public Multimap<LocalDate, String> getWorkedPositions() {
		return workedPositions;
	}

	public void setWorkedVacs(final Multimap<LocalDate, String> workedPositions) {
		this.workedPositions = workedPositions;
	}

	public List<String> getRefusedPositions() {
		return refusedPositions;
	}

	public void setRefusedPositions(final List<String> refusedPositions) {
		this.refusedPositions = refusedPositions;
	}

	public int getInternalIndice() {
		return internalIndice;
	}

	public void setInternalIndice(final Integer internalIndice) {
		this.internalIndice = internalIndice;
	}
	
	@Override
	public String toString() {
		return name + " : (timepart = " + timePart + "; internalindice = " + internalIndice + ")"; 
	}
	
	public Employee clone() {
		final Employee clonedWorker = new Employee();
		clonedWorker.setInternalIndice(internalIndice);
		clonedWorker.setName(name);
		clonedWorker.setPaidVacations(new ArrayList<>(paidVacations));
		clonedWorker.setRefusedPositions(new ArrayList<>(refusedPositions));
		clonedWorker.setTimePart(timePart);
		clonedWorker.setUnpaidVacations(new ArrayList<>(unpaidVacations));
		clonedWorker.setWorkedVacs(HashMultimap.create(workedPositions));
		return clonedWorker;
	}

}
