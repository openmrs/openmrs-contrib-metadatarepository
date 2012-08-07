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

import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayOutputStream;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.openmrs.contrib.metadatarepository.model.MetadataPackage;

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
		request.setRemoteUser("user");
		
		pkg.setName("Surgery");
		pkg.setDescription("Hospital Albert Schweitzer Haiti Surgery Form");
		pkg.setUser(umagr.getUserByUsername("user"));
		pkg.setVersion(1L);
		pkg.setOpenmrsVersion("1.8.3  Build 24510");
		pkg.setDateCreated(new Date());
		pkg.setGroupUuid("6ca304bf-9b70-4063-a669-c57d710d55aa");
		pkg.setSubscriptionUrl("");
        pkg.setDownloadCount(0L);
		InputStream fis = getClass().getResourceAsStream("/Surgery.zip");
		if(fis!=null){
		ByteArrayOutputStream data = new ByteArrayOutputStream(fis.available());
		IOUtils.copy(fis, data);
		pkg.setFile(data.toByteArray());
		}
		request.addParameter("upload", "");

		BindingResult errors = new DataBinder(pkg).getBindingResult();
		String test = f.onSubmit(pkg, errors, request);

		assertNotNull(test);

	}

}
