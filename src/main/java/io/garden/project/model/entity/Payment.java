package io.garden.project.model.entity;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "PAYMENT")
@ApiModel(description = "Class representing a Payment tracked by the application.")
public class Payment implements Serializable {
	
	/**
	 * Default Serial ID.
	 */
	private static final long serialVersionUID = 2904547345719167780L;
	
	/**
	 * Payment ID.
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	@ApiModelProperty(notes = "Unique identifier of the Employee. No two Payments can have the same id.", example = "1", required = true, position = 0)
	private Long id;
	
	@Size(max = 40)
	@Column(name = "PAYMENT_TYPE", length = 40, nullable = false)
	private String paymentType;
	
	@Size(max = 50)
	@Column(name = "ID_TRANSACTION", length = 50, nullable = false)
	private String idTransaction;
	
	@Column(name = "PAYMENT_DATE", nullable = false)
	private Date paymentDate;
	
	@Column(name = "TOTAL", nullable = false)
	private Long total;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "ID_CLIENT", foreignKey = @ForeignKey(name = "FK_PAYMENT_CLIENT"), nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	@JsonProperty("client")
	private Client client;

}
