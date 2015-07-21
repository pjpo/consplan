package pjpo.github.com.consplan.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="vacation")
public class Vacation implements Serializable {

	/** Generated seria id */
	private static final long serialVersionUID = -3320046876825419440L;

	/* Vacation internal id */
	@Id
	@GeneratedValue
	@Column(name="vacationid")
	private Long vacationId;
	
	@OneToOne
	@Column(name="vacationinterval")
	private IntervalDateTime interval;

	@Column(name="vacationpaid")
	private Boolean paid;	
	
	public Long getVacationId() {
		return vacationId;
	}

	public void setVacationId(Long vacationId) {
		this.vacationId = vacationId;
	}

	public IntervalDateTime getInterval() {
		return interval;
	}

	public void setInterval(IntervalDateTime interval) {
		this.interval = interval;
	}

	public Boolean getPaid() {
		return paid;
	}

	public void setPaid(Boolean paid) {
		this.paid = paid;
	}
	
}
