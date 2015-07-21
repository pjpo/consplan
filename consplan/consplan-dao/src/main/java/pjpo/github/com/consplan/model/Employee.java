package pjpo.github.com.consplan.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.github.pjpo.consplan.library.utils.IntervalDateTime;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

/**
 * Bean for Worker definition
 * @author jp@dm.lan
 *
 */
@Entity
@Table(name="employee")
public class Employee implements com.github.pjpo.consplan.library.model.Employee, Serializable {
    
	/** Generated serial id */
	private static final long serialVersionUID = 6994651473691083790L;

	/** Workers Internal Id */
	@Id
	@GeneratedValue
	@Column(name="employeeid")
    private Long employeeId;
	
	/** Worker's name */
    @NotNull(message = "{nonull}")
    @Size(min = 2, message = "{employee.name.size}")
    @Column(name="employeename")
    private String name;
	
	/** Part time (can be of any scale) */
    @NotNull(message = "{nonull}")
    @Min(value = 0, message = "{employee.timepart.min}")
    @Column(name="employeetimepart")
    private Integer timePart = 100;
	
	/** List of vacations */
    @OneToMany(mappedBy="employeeId")
    private Collection<Vacation> vacations;

	/** List of predefined worked positions */
    @OneToMany(mappedBy="employeeId")
	private Collection<Position> workedPositions;
	
	/** List of positions that this worker can not fill */
    @OneToMany(mappedBy="employeeId")
	private Collection<RefusedPosition> refusedPositions;

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

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
		final LinkedList<IntervalDateTime> paidVacations = new LinkedList<>();
		vacations.stream().filter((vacation) -> vacation.getPaid()).forEach((vacation) -> paidVacations.add(vacation.getInterval()));
		return paidVacations;
	}

	public List<IntervalDateTime> getUnpaidVacations() {
		final LinkedList<IntervalDateTime> unpaidVacations = new LinkedList<>();
		vacations.stream().filter((vacation) -> !vacation.getPaid()).forEach((vacation) -> unpaidVacations.add(vacation.getInterval()));
		return unpaidVacations;
	}

	public Multimap<LocalDate, String> getWorkedPositions() {
		final Multimap<LocalDate, String> workedPositionsMultimap = HashMultimap.create();
		workedPositions.forEach((workedPosition) -> workedPositionsMultimap.put(workedPosition.getLocalDate(), workedPosition.getPosition()));
		return workedPositionsMultimap;
	}

	public Collection<String> getRefusedPositions() {
		final ArrayList<String> refusedPositionsList = new ArrayList<>(refusedPositions.size());
		refusedPositions.forEach((refusedPosition) -> refusedPosition.getPositionName());
		return refusedPositionsList;
	}

	public String toString() {
		return name + " : (timepart = " + timePart + "; employeeId = " + employeeId + ")"; 
	}

}
