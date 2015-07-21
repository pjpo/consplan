package com.github.pjpo.consplan.library.model;

import java.time.LocalDate;
import java.util.Collection;
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

	public Integer getTimePart();

	public List<IntervalDateTime> getPaidVacations();

	public List<IntervalDateTime> getUnpaidVacations();

	public Multimap<LocalDate, String> getWorkedPositions();

	public Collection<String> getRefusedPositions();

}
