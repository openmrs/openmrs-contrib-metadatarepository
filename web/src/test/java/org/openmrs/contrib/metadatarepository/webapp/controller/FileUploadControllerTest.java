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
package org.openmrs.contrib.metadatarepository.webapp.controller;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.junit.Test;
import org.openmrs.contrib.metadatarepository.model.MetadataPackage;
import org.openmrs.contrib.metadatarepository.model.User;
import org.openmrs.contrib.metadatarepository.service.APIException;
import org.openmrs.contrib.metadatarepository.service.UserManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;

import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

public class FileUploadControllerTest extends BaseControllerTestCase {

	@Autowired
	private FileUploadController f = null;
	private MockHttpServletRequest request;
	private MetadataPackage pkg = new MetadataPackage();
	@Autowired
	private UserManager umagr;

	@Test
	public void testOnSubmit() throws Exception {
		
		
		request = newPost("/packageupload.html");
		pkg.setDescription("Labmodule");
		
		pkg.setName("Lab");
		pkg.setUser(umagr.getUserByUsername("user"));
		pkg.setVersion(new Long(1));
		
	    InputStream fis = getClass().getResourceAsStream("/src/test/resources/sample-data.xml");
		byte[] data = new byte[fis.available()];
		pkg.setFile(data);
		request.addParameter("upload", "");
		
		BindingResult errors = new DataBinder(pkg).getBindingResult();
		String test = f.onSubmit(pkg, errors, request);

		assertEquals("redirect:/packageform", test);

	}

}
