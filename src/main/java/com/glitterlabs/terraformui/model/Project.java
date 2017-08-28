package com.glitterlabs.terraformui.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * The Entity Project.
 */
@Entity
public class Project {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PROJECT_ID")
	private Long id;

	/** The name. */
	@Column(name = "NAME", nullable = false)
	private String name;

	/** The staus. */
	@Column(name = "STATUS", nullable = false)
	@Enumerated(EnumType.STRING)
	private ProjectStatus status;

	/** The date. */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	@Column(name = "CREATED_DATE", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	/** The cloud type. */
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CLOUD_ID", nullable = false)
	private Cloud cloudType;

	/** The path. */
	@Column(name = "PATH", nullable = false)
	private String path;

	/**
	 * Instantiates a new project.
	 */
	public Project() {
		// JPA
	}

	/**
	 * Instantiates a new project.
	 *
	 * @param name the name
	 */
	public Project(final String name) {
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

	/**
	 * Gets the cloud type.
	 *
	 * @return the cloud type
	 */
	public Cloud getCloudType() {
		return this.cloudType;
	}

	/**
	 * Sets the cloud type.
	 *
	 * @param cloudType the new cloud type
	 */
	public void setCloudType(final Cloud cloudType) {
		this.cloudType = cloudType;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(final Date date) {
		this.date = date;
	}

	public ProjectStatus getStatus() {
		return this.status;
	}

	public void setStatus(final ProjectStatus status) {
		this.status = status;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(final String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "Project [id=" + this.id + ", name=" + this.name + ", date=" + this.date + ", cloudType=" + this.cloudType.getName() + "]";
	}

	public static enum ProjectStatus {
		CREATED, ACTIVE
	}
}
