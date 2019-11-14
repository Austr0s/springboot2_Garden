package io.garden.project.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author Austr0s
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, of = "id")
@Builder
@Entity
@Table(name = "ROLE")
public class Role implements Serializable {

	/**
	 * Serial ID.
	 */
	private static final long serialVersionUID = -4451972217280551456L;

	/**
	 * Role ID.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	/**
	 * Role Code.
	 */
	@Size(max = 50)
	@Column(name = "CODE", length = 50, nullable = false)
	private String code;

	/**
	 * Role Description.
	 */
	@Size(max = 50)
	@Column(name = "DESCRIPTION", length = 50, nullable = false)
	private String description;

	@Override
	public String toString() {
		String tostring = null;
		if (id != null)
			tostring = id.toString();
		return tostring;
	}

}
