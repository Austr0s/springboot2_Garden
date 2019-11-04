package io.garden.project.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, of = "id")
@Builder
@Entity
@Table(name = "CLIENT")
@ApiModel(description = "Class representing a Client tracked by the application.")
public class Client implements Serializable {

	/**
	 * Default Serial ID.
	 */
	private static final long serialVersionUID = 1938275017379171051L;

	/**
	 * Client ID.
	 */
	@Id
	@GenericGenerator(name = "clientGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "SEQ_CLIENT") })
	@GeneratedValue(generator = "clientGenerator")
	@Column(name = "ID")
	@ApiModelProperty(notes = "Unique identifier of the Client. No two Clients can have the same id.", example = "1", required = true, position = 0)
	private Long id;

	/**
	 * Client Name.
	 */
	@Size(max = 50)
	@Column(name = "NAME", length = 50, nullable = false)
	@ApiModelProperty(notes = "Name of Client.", example = "Gardening Associates", required = true, position = 1)
	private String name;

	/**
	 * Client Contact Name.
	 */
	@Size(max = 30)
	@Column(name = "CONTACT_NAME", length = 30, nullable = true)
	@ApiModelProperty(notes = "Client Contact Name.", example = "Anne", required = false, position = 2)
	private String contactName;

	/**
	 * Client Contact First Name.
	 */
	@Size(max = 30)
	@Column(name = "CONTACT_FIRST_NAME", length = 30, nullable = true)
	@ApiModelProperty(notes = "Client Contact First Name.", example = "GoldFish", required = false, position = 3)
	private String contactFirstName;

	/**
	 * Client Phone
	 */
	@Size(max = 15)
	@Column(name = "PHONE", length = 15, nullable = false)
	private String phone;

	/**
	 * Client Fax
	 */
	@Size(max = 15)
	@Column(name = "FAX", length = 15, nullable = false)
	private String fax;

	/**
	 * Client Line Direction 1
	 */
	@Size(max = 50)
	@Column(name = "LINE_DIRECTION1", length = 50, nullable = false)
	private String lineDirection1;

	/**
	 * Client Line Direction 2
	 */
	@Size(max = 50)
	@Column(name = "LINE_DIRECTION2", length = 50, nullable = false)
	private String lineDirection2;

	/**
	 * Client City
	 */
	@Size(max = 50)
	@Column(name = "CITY", length = 50, nullable = false)
	private String city;

	/**
	 * Client Region
	 */
	@Size(max = 50)
	@Column(name = "REGION", length = 50, nullable = false)
	private String region;

	/**
	 * Client Country
	 */
	@Size(max = 50)
	@Column(name = "COUNTRY", length = 50, nullable = false)
	private String country;

	/**
	 * Client ZipCode
	 */
	@Size(max = 10)
	@Column(name = "ZIP_CODE", length = 10, nullable = false)
	private String zipCode;

	/**
	 * Client ZipCode
	 */
	@Column(name = "CREDIT_LIMIT", nullable = false)
	private Long creditLimit;

	/**
	 * Sales Employee Id
	 */
	// TODO: RelationShip with Employee Entity

}
