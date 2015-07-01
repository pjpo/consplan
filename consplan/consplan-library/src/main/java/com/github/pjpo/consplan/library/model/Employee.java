package com.github.pjpo.consplan.library.model;

import java.time.LocalDate;
import java.util.List;

import com.github.pjpo.consplan.library.utils.IntervalDateTime;
import com.google.common.collect.Multimap;

/**
 * Bean for Worker definition
 * @author jp@dm.lan
 *
 */
public interface Employee {

	public String getName();

	public void setName(final String name);

	public Integer getTimePart();

	public void setTimePart(final Integer timePart);

	public List<IntervalDateTime> getPaidVacations();
	
	public void setPaidVacations(final List<IntervalDateTime> paidVacations);

	public List<IntervalDateTime> getUnpaidVacations();

	public void setUnpaidVacations(final List<IntervalDateTime> unpaidVacations);

	public Multimap<LocalDate, String> getWorkedPositions();

	public void setWorkedVacs(final Multimap<LocalDate, String> workedPositions);

	public List<String> getRefusedPositions();

	public void setRefusedPositions(final List<String> refusedPositions);

}
