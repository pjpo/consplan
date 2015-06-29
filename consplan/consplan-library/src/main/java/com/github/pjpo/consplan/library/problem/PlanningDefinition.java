package com.github.pjpo.consplan.library.problem;

import java.util.HashMap;
import java.util.List;

import com.github.pjpo.consplan.library.model.PositionConstraintBase;
import com.github.pjpo.consplan.library.model.PositionDefinition;
import com.github.pjpo.consplan.library.model.Employee;
import com.github.pjpo.consplan.library.utils.IntervalDate;

/**
 * Definitions of the constraints for the planning :
 *  <ul>
 *   <li>Persons</li>
 *   <li>Working positions</li>
 *   <li>Inter-day and intra-day constraints</li>
 * @author jpc
 *
 */
public class PlanningDefinition {

	/** List of used physicians with their internal indice number*/
	private final HashMap<Integer, Employee> physicians;
	
	/** List of positions definitions */
	private final List<PositionDefinition> positionsDefinitions;
	
	/** List of intra and interday constraints */
	private final List<PositionConstraintBase> positionsConstraints;
		
	/**
	 * Creates the planning constraints from persons who can work and positions definitions
	 * @param physicians
	 * @param positionsCode
	 */
	public PlanningDefinition(
			final HashMap<Integer, Employee> physicians,
			final List<PositionDefinition> positionsCode,
			final List<PositionConstraintBase> positionsConstraints) {
		this.physicians = physicians;
		this.positionsDefinitions = positionsCode;
		this.positionsConstraints = positionsConstraints;
	}
	
	/**
	 * Generates the planning from the current definitions for the defined interval
	 * @param interval
	 * @return
	 */
	public final PlanningForInterval generatePlanningImplementation(final IntervalDate interval) {
		return new PlanningForInterval(interval, physicians, positionsDefinitions, positionsConstraints);
	}
	
	/**
	 * Returns the defined possible employees with their defined indice
	 * @return
	 */
	public HashMap<Integer, Employee> getPhysicians() {
		return physicians;
	}
	
	/**
	 * Returns the definitions for the positions
	 * @return
	 */
	public List<PositionDefinition> getPositionsDefinitions() {
		return positionsDefinitions;
	}
	
	/**
	 * Returns the definitions for the constraints
	 * @return
	 */
	public List<PositionConstraintBase> getPositionsConstraints() {
		return positionsConstraints;
	}
}