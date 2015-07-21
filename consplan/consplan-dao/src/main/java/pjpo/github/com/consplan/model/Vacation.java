package pjpo.github.com.consplan.model;

import com.github.pjpo.consplan.library.utils.IntervalDateTime;

public class Vacation {

	private IntervalDateTime interval;
	
	private Boolean paid;

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
