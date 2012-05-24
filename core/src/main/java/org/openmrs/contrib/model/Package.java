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

package org.openmrs.contrib.model;

import org.openmrs.model.BaseObject;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import java.util.HashSet;
import java.util.LinkedHashSet;

@Entity @Table(name="package")
public class Package extends BaseObject {
    private Long id;
    private String packageName;
    private String packageDescription;
    private Long packageVersion;
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="package_name", length=50)
    public String getPackageName() {
        return packageName;
    }
    
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
    
    @Column(name="package_description", length=100) 
    public String getPackageDescription() {
        return packageDescription;
    }

    public void setPackageDescription(String packageDescription) {
        this.packageDescription = packageDescription;
    }

    @Column(name="package_version", length=10)
    public Long getVersion() {
        return packageVersion;
    }
    
    public void setPackageVersion(Long packageVersion) {
        this.packageVersion = packageVersion;
    }
    
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Package package = (Package) o;

        if (packageName != null ? !packageName.equals(package.packageName) : package.packageName != null) 
        	return false;
        if (packageDescription != null ? !packageDescription.equals(package.packageDescription) : package.packageDescription != null)
        	return false;
        if (packageVersion != null ? !packageVersion.equals(package.packageVersion) : package.packageVersion != null)
        	return false;
        
        return true;
    }
    
    public int hashCode() {
        return (packageName != null ? packageName.hashCode() : 0);
    }
    public String toString() {
        return "Package{" +
                "id=" + id +
                ", packageName='" + packageName + '\'' +
                ", packageDescription='" + packageDescription + '\'' +
                ", packageVersion='" + packageVersion + '\'' +
                '}';
    }


    
    

}