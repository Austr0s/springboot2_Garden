package io.garden.project.model.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
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
@Table(name = "EMPLOYEE")
@ApiModel(description = "Class representing a Employee tracked by the application.")
public class Employee implements Serializable {
	
	/**
	 * Default serial ID. 
	 */
	private static final long serialVersionUID = 3631942270483985538L;
	
	/**
	 * Employee ID.
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	@ApiModelProperty(notes = "Unique identifier of the Employee. No two Employees can have the same id.", example = "1", required = true, position = 0)
	private Long id;
	
	@Size(max = 50)
	@Column(name = "NAME", length = 50, nullable = false)
	@ApiModelProperty(notes = "Employee's Name.", example = "Jhon", required = true, position = 1)
	private String name;
	
	@Size(max = 50)
	@Column(name = "FIRSTNAME", length = 50, nullable = false)
	@ApiModelProperty(notes = "Employee's FirstName.", example = "Gallaguer", required = true, position = 2)
	private String firstName;

	@Size(max = 50)
	@Column(name = "LASTNAME", length = 50, nullable = true)
	@ApiModelProperty(notes = "Employee's FirstName.", example = "Goldberg", required = false, position = 3)
	private String lastName;
	
	@Size(max = 10)
	@Column(name = "EXTENSION", length = 10, nullable = false)
	@ApiModelProperty(notes = "Employee's Extension.", example = "123456", required = true, position = 4)
	private String extension;
	
	@Size(max = 100)
	@Column(name = "EMAIL", length = 100, nullable = false)
	@ApiModelProperty(notes = "Employee's Email.", example = "employee@email.com", required = true, position = 5)
	private String email;
	
	@Size(max = 50)
	@Column(name = "WORKSTATION", length = 50, nullable = true)
	@ApiModelProperty(notes = "Employee's WorkStation.", example = "CEO", required = false, position = 6)
	private String workstation;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "ID_OFFICE", foreignKey = @ForeignKey(name = "FK_EMPLOYEE_OFFICE"), nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE) 
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	@JsonProperty("office")
	@ApiModelProperty(notes = "Employee's Office.", example = "321", required = true, position = 7)
	private Office office;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true, cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_BOSS_EMPLOYEE", nullable = true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	@JsonProperty("bossEmployee")
	@ApiModelProperty(notes = "Employee's Boss.", example = "123", required = false, position = 8)
	private Employee bossEmployee;
	
	
	@Override
	public String toString() {
		String tostring = null;
		if (id != null) {
			tostring = id.toString();
		}
		return tostring;
	}
	
}
