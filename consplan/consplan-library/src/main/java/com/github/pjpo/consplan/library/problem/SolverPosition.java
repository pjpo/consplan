package com.github.pjpo.consplan.library.problem;

import org.chocosolver.solver.Solver;
import org.chocosolver.solver.variables.IntVar;

import com.github.pjpo.consplan.library.utils.IntervalDateTime;

/**
 * Represents an actual position in planning, having multiple fields :
 * <ul>
 *   <li>External datas : name of position, date, workload, ... </li>
 *   <li>Internal datas : an {@link IntVar} which represents the internal representation in Choco Solver</li>
 * </ul>
 * @author jp@dm.lan
 *
 */
public class SolverPosition implements Cloneable {

	/** Name of this position */
	private String name;

	/** Bounds of the position */
	private IntervalDateTime bounds;

	/** Is the position active this day or not */
	private Boolean isActive;
	
	/** Workload for this position (can be different from time bounds) */
	private Integer workLoad;
	
	/** Internal representation for Choco for this position */
	private IntVar internalChocoRepresentation;
	
	/** Worker at this position */
	private SolverEmployee worker;
	
	/**
	 * Returns bounds of this position
	 * @return
	 */
	public IntervalDateTime getBounds() {
		return bounds;
	}

	/**
	 * Sets bounds of this position
	 * @param bounds
	 */
	public void setBounds(final IntervalDateTime bounds) {
		this.bounds = bounds;
	}

	/**
	 * Returns if this position is active this day
	 * @return
	 */
	public Boolean getIsActive() {
		return isActive;
	}

	/**
	 * Sets if this position is active this day
	 * @param isActive
	 */
	public void setIsActive(final Boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * Gets the workload defined for this position
	 * @return
	 */
	public Integer getWorkLoad() {
		return workLoad;
	}

	/**
	 * Sets the workload defined for this position
	 * @param workLoad
	 */
	public void setWorkLoad(final Integer workLoad) {
		this.workLoad = workLoad;
	}

	/**
	 * Gets the declared name for this position
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the declared name for this position
	 * @param name
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * Gets the variable correspondance for the choco {@link Solver}
	 * @return
	 */
	public IntVar getInternalChocoRepresentation() {
		return internalChocoRepresentation;
	}

	/**
	 * Sets the variable correspondance for the choco {@link Solver}
	 * @param internalChocoRepresentation
	 */
	public void setInternalChocoRepresentation(final IntVar internalChocoRepresentation) {
		this.internalChocoRepresentation = internalChocoRepresentation;
	}
	
	/**
	 * Gets the Employee defined for this position
	 * @return
	 */
	public SolverEmployee getWorker() {
		return worker;
	}

	/**
	 * Sets the employee for this position at this day
	 * @param worker
	 */
	public void setWorker(final SolverEmployee worker) {
		this.worker = worker;
	}
	
	@Override
	public Object clone() {
		final SolverPosition clonedPosition = new SolverPosition();
		clonedPosition.setBounds(bounds);
		clonedPosition.setIsActive(isActive);
		clonedPosition.setWorkLoad(workLoad);
		clonedPosition.setName(name);
		clonedPosition.setInternalChocoRepresentation(internalChocoRepresentation);
		clonedPosition.setWorker(worker);
		return clonedPosition;
	}
	
}
