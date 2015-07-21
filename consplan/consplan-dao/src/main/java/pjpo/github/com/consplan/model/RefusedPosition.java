package pjpo.github.com.consplan.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="refusedposition")
public class RefusedPosition implements Serializable {

	/** Generated serial Id */
	private static final long serialVersionUID = -9066981571557548359L;

	/** Refused Position Internal Id */
	@Id
	@GeneratedValue
	@Column(name="refusedpositionid")
	private Long refusedPositionId;

	/** Name of the refused position */
	@NotNull
	@NotEmpty
	@Column(name="refusedpositionname")
	private String positionName;

	public Long getRefusedPositionId() {
		return refusedPositionId;
	}

	public void setRefusedPositionId(Long refusedPositionId) {
		this.refusedPositionId = refusedPositionId;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

}
