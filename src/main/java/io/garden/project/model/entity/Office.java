package io.garden.project.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@Table(name = "OFFICE")
@ApiModel(description = "Class representing a Office tracked by the application.")
public class Office implements Serializable {

	/**
	 * Default Serial ID.
	 */
	private static final long serialVersionUID = 5002473950037365011L;

	/**
	 * Office ID.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	@ApiModelProperty(notes = "Unique identifier of the Office. No two Offices can have the same id.", example = "1", required = true, position = 0)
	private Long id;

	/**
	 * Office Code.
	 */
	@Size(max = 10)
	@Column(name = "CODE", length = 10, nullable = false)
	@ApiModelProperty(notes = "Office's Code.", example = "BOS-USA", required = true, position = 1)
	private String code;

	/**
	 * Office City
	 */
	@Size(max = 30)
	@Column(name = "CITY", length = 30, nullable = false)
	@ApiModelProperty(notes = "Office's City.", example = "Miami", required = false, position = 2)
	private String city;

	/**
	 * Office Region
	 */
	@Size(max = 50)
	@Column(name = "REGION", length = 50, nullable = false)
	@ApiModelProperty(notes = "Office's Region.", example = "Miami", required = false, position = 3)
	private String region;

	/**
	 * Office Country
	 */
	@Size(max = 50)
	@Column(name = "COUNTRY", length = 50, nullable = false)
	@ApiModelProperty(notes = "Office's Country.", example = "USA", required = false, position = 4)
	private String country;

	/**
	 * Office ZipCode
	 */
	@Size(max = 10)
	@Column(name = "ZIP_CODE", length = 10, nullable = false)
	@ApiModelProperty(notes = "Office's ZIP Code.", example = "24006", required = false, position = 5)
	private String zipCode;

	/**
	 * Office Phone
	 */
	@Size(max = 20)
	@Column(name = "PHONE", length = 20, nullable = false)
	@ApiModelProperty(notes = "Office's Phone Number.", example = "+34 912365498", required = false, position = 6)
	private String phone;

	/**
	 * Office Line Direction 1
	 */
	@Size(max = 50)
	@Column(name = "LINE_DIRECTION1", length = 50, nullable = false)
	@ApiModelProperty(notes = "Office's line direction 1.", example = "False Street 52 2 A", required = false, position = 7)
	private String lineDirection1;

	/**
	 * Office Line Direction 2
	 */
	@Size(max = 50)
	@Column(name = "LINE_DIRECTION2", length = 50, nullable = false)
	@ApiModelProperty(notes = "Office's line direction 2.", example = "False Street 52 2 B", required = false, position = 8)
	private String lineDirection2;

	@Override
	public String toString() {
		String tostring = null;
		if (id != null)
			tostring = id.toString();
		return tostring;
	}
}
