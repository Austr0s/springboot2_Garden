package io.garden.project.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sun.istack.NotNull;

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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
	@ApiModelProperty(notes = "Client's Contact Name.", example = "Anne", required = false, position = 2)
	private String contactName;

	/**
	 * Client Contact First Name.
	 */
	@Size(max = 30)
	@Column(name = "CONTACT_FIRST_NAME", length = 30, nullable = true)
	@ApiModelProperty(notes = "Client's Contact First Name.", example = "GoldFish", required = false, position = 3)
	private String contactFirstName;

	/**
	 * Client Phone
	 */
	@Size(max = 15)
	@Column(name = "PHONE", length = 15, nullable = false)
	@ApiModelProperty(notes = "Client's Phone Number.", example = "+34 912365498", required = false, position = 4)
	private String phone;

	/**
	 * Client Fax
	 */
	@Size(max = 15)
	@Column(name = "FAX", length = 15, nullable = false)
	@ApiModelProperty(notes = "Client's Fax Number.", example = "+34 912365498", required = false, position = 5)
	private String fax;

	/**
	 * Client Line Direction 1
	 */
	@Size(max = 50)
	@Column(name = "LINE_DIRECTION1", length = 50, nullable = false)
	@ApiModelProperty(notes = "Client's line direction 1.", example = "False Street 52 2 A", required = false, position = 6)
	private String lineDirection1;

	/**
	 * Client Line Direction 2
	 */
	@Size(max = 50)
	@Column(name = "LINE_DIRECTION2", length = 50, nullable = false)
	@ApiModelProperty(notes = "Client's line direction 2.", example = "False Street 52 2 B", required = false, position = 7)
	private String lineDirection2;

	/**
	 * Client City
	 */
	@Size(max = 50)
	@Column(name = "CITY", length = 50, nullable = false)
	@ApiModelProperty(notes = "Client's City.", example = "Miami", required = false, position = 8)
	private String city;

	/**
	 * Client Region
	 */
	@Size(max = 50)
	@Column(name = "REGION", length = 50, nullable = false)
	@ApiModelProperty(notes = "City's Region.", example = "Miami", required = false, position = 9)
	private String region;

	/**
	 * Client Country
	 */
	@Size(max = 50)
	@Column(name = "COUNTRY", length = 50, nullable = false)
	@ApiModelProperty(notes = "Client's Country.", example = "USA", required = false, position = 10)
	private String country;

	/**
	 * Client ZipCode
	 */
	@Size(max = 10)
	@Column(name = "ZIP_CODE", length = 10, nullable = false)
	@ApiModelProperty(notes = "Client's ZIP Code.", example = "24006", required = false, position = 11)
	private String zipCode;

	/**
	 * Client ZipCode
	 */
	@Column(name = "CREDIT_LIMIT", nullable = false)
	@ApiModelProperty(notes = "Client's Credit Limit.", example = "12000.00", required = false, position = 12)
	private Long creditLimit;

	/**
	 * Sales Employee Id
	 */
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "ID_SALES_REP_EMPLOYEE", foreignKey = @ForeignKey(name = "FK_SALES_REP_EMPLOYEE"), nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	@JsonProperty("employee")
	private Employee employee;

	
	@Override
	public String toString() {
		String tostring = null;
		if (id != null) {
			tostring = id.toString();
		}
		return tostring;
	}

}
