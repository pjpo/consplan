package pjpo.github.com.consplan.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="intervaldatetime")
public class IntervalDateTime extends com.github.pjpo.consplan.library.utils.IntervalDateTime {

	public Long intervalId;

	@Id
	@GeneratedValue
	@Column(name="intervalid")
	public Long getIntervalId() {
		return intervalId;
	}

	public void setIntervalId(Long intervalId) {
		this.intervalId = intervalId;
	}

	@Column(name="intervalstart")
	public LocalDateTime getStart() {
		return super.getStart();
	}
	
	@Column(name="intervalend")
	public LocalDateTime getEnd() {
		return super.getEnd();
	}

	
}
