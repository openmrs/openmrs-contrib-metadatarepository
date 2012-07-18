/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */

package org.openmrs.contrib.metadatarepository.model;

import java.util.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.compass.annotations.Searchable;
import org.compass.annotations.SearchableId;
import org.compass.annotations.SearchableProperty;
import org.compass.annotations.SearchableReference;

@Entity
@Table(name = "package")
@Searchable
public class MetadataPackage extends BaseObject {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String description;
	private Long version;
	private String openmrsVersion;
	private byte[] file;
	private User user;
	private Long downloadCount = 0L;
	private Date dateCreated;
	private String groupUuid;
	private String subscriptionUrl;
	private Map<String, String> modules;

	/**
	 * Default constructor - creates a new instance with no values set.
	 */
	public MetadataPackage() {

	}

	/**
	 * Create a new instance and set the name.
	 * 
	 * @param name
	 *            name of the package.
	 */
	public MetadataPackage(final String name) {
		this.name = name;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@SearchableId(name = "id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	@Column(name = "name", length = 50)
	@SearchableProperty
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	@Column(name = "description", length = 255)
	@SearchableProperty
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the version
	 */
	@Column(name = "version")
	@SearchableProperty
	public Long getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(Long version) {
		this.version = version;
	}

	/**
	 * @return the openmrsVersion
	 */

	@Column(name = "openmrsVersion")
	@SearchableProperty
	public String getOpenmrsVersion() {
		return openmrsVersion;
	}

	/**
	 * @param openmrsVersion
	 *            the openmrsVersion to set
	 */
	public void setOpenmrsVersion(String openmrsVersion) {
		this.openmrsVersion = openmrsVersion;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	@Transient
	public byte[] getFile() {
		return file;
	}

	@ManyToOne
	@JoinColumn(name = "user_id")
	@SearchableReference
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Transient
	@SearchableProperty
	private Long getUserId() {
		return user.getId();
	}

	/**
	 * @return the downloadCount
	 */
	@Column(name = "downloadCount")
	@SearchableProperty
	public Long getDownloadCount() {
		return downloadCount;
	}

	/**
	 * @param downloadCount
	 *            the downloadCount to set
	 * 
	 */
	public void setDownloadCount(Long downloadCount) {
		this.downloadCount = downloadCount;
	}

	/**
	 * @return the dateCreated
	 * 
	 */

	@Column(name = "dateCreated")
	@SearchableProperty
	public Date getDateCreated() {
		return dateCreated;
	}

	/**
	 * @param dateCreated
	 *            the dateCreated to set
	 * 
	 */
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	/**
	 * @return the groupUuid
	 * 
	 */
	@Column(name = "groupUuid")
	public String getGroupUuid() {
		return groupUuid;
	}

	/**
	 * @param groupUuid
	 *            the groupUuid to set
	 * 
	 */
	public void setGroupUuid(String groupUuid) {
		this.groupUuid = groupUuid;
	}

	/**
	 * The URL which points to the future package updates
	 * 
	 * @return the subscriptionUrl
	 * 
	 */
	@Column(name = "subscriptionUrl")
	public String getSubscriptionUrl() {
		return subscriptionUrl;
	}

	/**
	 * The URL which points to the future package updates
	 * 
	 * @param subscriptionUrl
	 *            the subscriptionUrl to set
	 * 
	 */
	public void setSubscriptionUrl(String subscriptionUrl) {
		this.subscriptionUrl = subscriptionUrl;
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!getClass().equals(o.getClass()))
			return false;

		MetadataPackage p = (MetadataPackage) o;

		if (name != null ? !name.equals(p.name) : p.name != null)
			return false;
		if (description != null ? !description.equals(p.description)
				: p.description != null)
			return false;
		if (version != null ? !version.equals(p.version) : p.version != null)
			return false;

		return true;
	}

	public int hashCode() {
		return (name != null ? name.hashCode() : 0);
	}

	public String toString() {
		ToStringBuilder sb = new ToStringBuilder(this,
				ToStringStyle.DEFAULT_STYLE).append("name", this.name)
				.append("description", this.description)
				.append("version", this.version);

		return sb.toString();
	}

}