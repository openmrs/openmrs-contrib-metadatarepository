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

package org.openmrs.contrib.metadatarepository.service;

import java.util.List;

import junit.framework.Assert;

import org.openmrs.contrib.metadatarepository.model.MetadataPackage;
import org.openmrs.contrib.metadatarepository.model.User;
import org.openmrs.contrib.metadatarepository.service.PackageManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.contrib.metadatarepository.service.BaseManagerTestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class PackageManagerTest extends BaseManagerTestCase {
	private MetadataPackage pkg;
	private Log log = LogFactory.getLog(PackageManagerTest.class);
	@Autowired
	private PackageManager magr;

	@Autowired
	private UserManager umagr;

	@Test
	public void testGetPackage() throws Exception {

		pkg = magr.get(1L);
		assertNotNull(pkg);
		log.debug(pkg);
	}

	@Test
	public void testSavePackage() throws Exception {
		pkg = magr.get(1L);
		pkg.setDescription("test desc");

		log.debug("saving package with updated package description: " + pkg);
		pkg = magr.save(pkg);
		// assertEquals("test desc",pkg.getPackageDescription());

	}

	@Test
	public void testRemovePackage() throws Exception {
		pkg = magr.get(1L);
		magr.remove(1L);

	}

	/*@Test
	public void testSearchByUser() throws Exception {
		User user = umagr.getUserByUsername("user");
		String query = null;

		List<MetadataPackage> searchByUser = magr.searchByUser(query, user);
        Assert.assertEquals(1, searchByUser.size());
	}*/

}
