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

package org.openmrs.contrib.service;

import org.openmrs.contrib.dao.PackageDao;
import org.openmrs.contrib.model.Package;


import java.util.List;

public interface PackageManager extends GenericManager<Package, Long> {
	
	/**
     * Convenience method for testing - allows you to mock the DAO and set it on an interface.
     * @param packageDao the PackageDao implementation to use
     */
    void setPackageDao(PackageDao packageDao);
    
    /**
     * Retrieves a package by packageId.  An exception is thrown if package not found
     *
     * @param packageId the identifier for the package
     * @return Package
     */
    Package getPackage(String packageId);
    
    /**
     * Finds a package by their packagename.
     * @param packagename
     * @return Package a populated package object
     */
    Package getPackageByPackagename(String packageName);
    
    /**
     * Retrieves a list of all users.
     * @return List
     */
    List<Package> getPackages();
    
    /**
     * Removes a package from the database by their packageId
     *
     * @param packageId the package's id
     */
    void removePackage(String packageId);
    
    /**
     * Search a package for search terms.
     * @param searchTerm the search terms.
     * @return a list of matches, or all if no searchTerm.
     */
    List<Package> search(String searchTerm);
    

}
