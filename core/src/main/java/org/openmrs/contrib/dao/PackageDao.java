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

package org.openmrs.contrib.dao;

import org.openmrs.contrib.model.Package;


public interface PackageDao extends GenericDao<Package, Long>{
	
	 /**
     * Gets package information based on packagename
     * @param packagename the packagename
     * @return populated package object
     */
	
   Package getPackageByName(String pkgname);
  
  
   /**
    * Removes a package from the database by name
    * @param packagename the package's name
    */
   void removePackage(String pkgname);
 
 
}
