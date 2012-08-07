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

import java.util.Locale;

import org.openmrs.contrib.metadatarepository.model.MetadataPackage;
import org.openmrs.contrib.metadatarepository.model.User;
import org.openmrs.contrib.metadatarepository.service.PackageManager;
import org.openmrs.contrib.metadatarepository.service.UserManager;
import org.openmrs.contrib.metadatarepository.service.impl.PackageManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller class to upload Files.
 * <p/>
 * <p>
 * <a href="FileUploadFormController.java.html"><i>View Source</i></a>
 * </p>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
@Controller
@RequestMapping("/packageupload*")
public class FileUploadController extends BaseFormController {

	@Autowired
	private PackageManager packageManager;
	private UserManager userManager = null;

	@Autowired
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public FileUploadController() {
		setCancelView("redirect:/mainMenu");
		setSuccessView("redirect:/packageform");
	}

	@ModelAttribute
	@RequestMapping(method = RequestMethod.GET)
	public MetadataPackage showForm() {
		return new MetadataPackage();
	}

	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(MetadataPackage metadataPackage,
			BindingResult errors, HttpServletRequest request) throws Exception {

		if (request.getParameter("cancel") != null) {
			return getCancelView();
		}
		Locale locale = request.getLocale();
		if (validator != null) { // validator is null during testing
			validator.validate(metadataPackage, errors);

			if (errors.hasErrors()) {
				return "packageupload";
			}
		}

		// validate a file was entered
		if (metadataPackage.getFile().length == 0) {
			Object[] args = new Object[] { getText("uploadForm.file",
					request.getLocale()) };
			errors.rejectValue("file", "errors.required", args, "File");

			return "packageupload";
		}
		saveMessage(request, getText("package.uploaded", locale));
		User uname;

		log.debug("" + userManager.getUserByUsername(request.getRemoteUser()));
		uname = userManager.getUserByUsername(request.getRemoteUser());

		// Deserializing the package
		MetadataPackage pkg = packageManager.deserializePackage(metadataPackage
				.getFile());
		metadataPackage.setUser(uname);
		metadataPackage.setFields(pkg);
		MetadataPackage meta = packageManager.savePackage(metadataPackage);
		Long id = meta.getId();

		return getSuccessView() + "?id=" + meta.getId();
	}
}
