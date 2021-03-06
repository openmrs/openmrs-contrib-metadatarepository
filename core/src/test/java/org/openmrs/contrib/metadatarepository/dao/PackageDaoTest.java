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

package org.openmrs.contrib.metadatarepository.dao;

import org.openmrs.contrib.metadatarepository.dao.PackageDao;
import org.openmrs.contrib.metadatarepository.model.MetadataPackage;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class PackageDaoTest extends BaseDaoTestCase{
	@Autowired
    private PackageDao dao;
	
	

	    @Test
	    public void testGetPackage() throws Exception {
	        MetadataPackage pkg = dao.get(1L);
	        assertNotNull(pkg);
	    }
	   
	    @Test
	    public void testSavePackage() throws Exception {
	        MetadataPackage pkg = dao.get(1L);
	        pkg.setDescription("test desc");
	        dao.save(pkg);
	        flush();
	        
	        assertEquals("test desc", pkg.getDescription());
	    }
       
	    @Test
	    public void testRemovePackage() throws Exception {
	        //Package pkg = new Package("testpkg");
	         MetadataPackage pkg =dao.get(1L);
	        dao.remove(1L);
	        flush();
	    }

	   
}
