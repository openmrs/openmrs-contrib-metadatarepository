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
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class PackageDaoTest extends BaseDaoTestCase{
	@Autowired
    private PackageDao dao;
	
	 @Test
	    public void testGetPackageInvalid() throws Exception {
	        Package pkg = dao.getPackageByName("badpackagename");
	        assertNull(pkg);
	    }

	    @Test
	    public void testGetPackage() throws Exception {
	        Package pkg = dao.getPackageByName("pkg");
	        assertNotNull(pkg);
	    }
	   
	    @Test
	    public void testGetPackageByDescription() throws Exception {
	        Package pkg = dao.getPackageByName("name");
	        pkg.setPackageDescription("test desc");
	        dao.save(pkg);
	        flush();
	        
	        pkg = dao.getPackageByName("name");
	        assertEquals("test desc", pkg.getPackageDescription());
	    }
       
	    @Test
	    public void testAddAndRemovePackage() throws Exception {
	        Package pkg = new Package("testpkg");
	        pkg.setPackageDescription("new pkg desc");
	        dao.save(pkg);
	        flush();
	        
	        pkg = dao.getPackageByName("testpkg");
	        assertNotNull(pkg.getPackageDescription());

	        dao.removePackage("testpkg");
	        flush();

	        pkg = dao.getPackageByName("testpkg");
	        assertNull(pkg);
	    }

	   
}
