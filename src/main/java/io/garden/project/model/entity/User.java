package io.garden.project.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

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
@Table(name = "USER")
public class User implements Serializable {
	
	/**
	 * Serial ID.
	 */
	private static final long serialVersionUID = -4446821374309356710L;

	/**
	 * User ID.
	 */
	@Id
	@GenericGenerator(name = "userGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "SEQ_USER") })
	@GeneratedValue(generator = "userGenerator")
	@Column(name = "ID")
	private Long id;
	
	/**
	 * Username.
	 */
	@Size(max = 50)
	@Column(name = "USERNAME", length = 50, nullable = false)
	private String userName;
	
	/**
	 * User Password.
	 */
	@Size(max = 50)
	@Column(name = "PASSWORD", length = 50, nullable = false)
	private String password;

	/**
	 * User Account State.
	 */
	@Type(type = "org.hibernate.type.NumericBooleanType")
	@Column(name = "ACTIVE")
	private Boolean active;
	
	/**
	 * Role entity.
	 */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
	private List<Role> roles;
	
	@Override
	public String toString() {
		String tostring = null;
		if (id != null) {
			tostring = id.toString();
		}
		return tostring;
	}
	
}
