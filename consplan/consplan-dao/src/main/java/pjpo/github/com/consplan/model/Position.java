package pjpo.github.com.consplan.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import pjpo.github.com.consplan.converter.LocalDatePersistenceConverter;

@Entity
@Table(name="position")
public class Position implements Serializable {

	/** Generated serial Id */
	private static final long serialVersionUID = -7736306843119280271L;

	/** Position Internal Id */
	@Id
	@GeneratedValue
	@Column(name="positionid")
	private Long positionId;
	
	/** Date for this position */
	@NotNull
	@Convert(converter = LocalDatePersistenceConverter.class)
	@Column(name="positiondate")
	private LocalDate localDate;
	
	
	/** Name of the position */
	@NotNull
	@NotEmpty
	@Column(name="positionname")
	private String position;

	public Long getPositionId() {
		return positionId;
	}

	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}

	public LocalDate getLocalDate() {
		return localDate;
	}

	public void setLocalDate(LocalDate localDate) {
		this.localDate = localDate;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

}
