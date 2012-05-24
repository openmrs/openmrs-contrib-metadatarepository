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
package org.openmrs.contrib.webapp.controller;

import org.apache.commons.lang.StringUtils;
import org.openmrs.contrib.service.GenericManager;
import org.openmrs.contrib.model.Package;
import org.openmrs.contrib.webapp.controller.BaseFormController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;


	@Controller
	@RequestMapping("/packageform*")
	public class PackageFormController extends BaseFormController {
	    private GenericManager<Package, Long> packageManager = null;
	 
	    @Autowired
	    public void setPackageManager(@Qualifier("packageManager") GenericManager<Package, Long> packageManager) {
	        this.packageManager = packageManager;
	    }
	 
	    public PackageFormController() {
	        setCancelView("redirect:package");
	        setSuccessView("redirect:package");
	    }
	 
	    @ModelAttribute
	    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
	    protected Package showForm(HttpServletRequest request)
	    throws Exception {
	        String id = request.getParameter("id");

	        if (!StringUtils.isBlank(id)) {
	            return packageManager.get(new Long(id));
	        }

	        return new Package();
	    }

	    @RequestMapping(method = RequestMethod.POST)
	    public String onSubmit(Package pkg, BindingResult errors, HttpServletRequest request,
	                           HttpServletResponse response)
	    throws Exception {
	        if (request.getParameter("cancel") != null) {
	            return getCancelView();
	        }

	        log.debug("entering 'onSubmit' method...");

	        boolean isNew = (pkg.getId() == null);
	        String success = getSuccessView();
	        Locale locale = request.getLocale();

	        if (request.getParameter("delete") != null) {
	            packageManager.remove(pkg.getId());
	            saveMessage(request, getText("person.deleted", locale));
	        } else {
	            packageManager.save(pkg);
	            String key = (isNew) ? "package.added" : "package.updated";
	            saveMessage(request, getText(key, locale));

	            if (!isNew) {
	                success = "redirect:packageform?id=" + pkg.getId();
	            }
	        }

	        return success;
	    }

	}

