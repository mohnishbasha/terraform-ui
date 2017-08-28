package com.glitterlabs.terraformui.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The Entity Cloud.
 */
@Entity
public class Cloud {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CLOUD_ID")
	private Long id;

	/** The name. */
	@Column(name = "NAME", nullable = false)
	private String name;

	/** The date. */
	@Column(name = "CREATED_DATE", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	/**
	 * Instantiates a new cloud.
	 */
	public Cloud() {
		// JPA
	}

	/**
	 * Instantiates a new cloud.
	 *
	 * @param id the id
	 */
	public Cloud(final Long id) {
		this.id = id;
	}

	/**
	 * Instantiates a new cloud.
	 *
	 * @param name the name
	 */
	public Cloud(final String name) {
		this.name = name;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Cloud [id=" + this.id + ", name=" + this.name + ", date=" + this.date + "]";
	}
}
