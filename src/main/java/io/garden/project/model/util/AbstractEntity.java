package io.garden.project.model.util;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 803402936016394712L;

	/**
	 * Date when User is created.
	 */
	@Column(name = "CREATED_AT", nullable = false)
	@CreatedDate
	private Date createdAt;

	/**
	 * Date when User is deleted.
	 */
	@Column(name = "DELETED_AT")
	private Date deletedAt;

	/**
	 * Date when User is updated.
	 */
	@Column(name = "UPDATED_AT")
	@LastModifiedDate
	private Date updatedAt;

}
