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

import org.openmrs.contrib.metadatarepository.model.MetadataPackage;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

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
@RequestMapping("/fileupload*")
public class FileUploadController extends BaseFormController {

    public FileUploadController() {
        setCancelView("redirect:/mainMenu");
        //setSuccessView("uploadDisplay");
        setSuccessView("redirect:/packageform");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    public MetadataPackage showForm() {
        return new MetadataPackage();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(MetadataPackage metadataPackage, BindingResult errors, HttpServletRequest request)
            throws Exception {

        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(metadataPackage, errors);

            if (errors.hasErrors()) {
                return "fileupload";
            }
        }

        // validate a file was entered
        if (metadataPackage.getFile().length == 0) {
            Object[] args =
                    new Object[]{getText("uploadForm.file", request.getLocale())};
            errors.rejectValue("file", "errors.required", args, "File");

            return "fileupload";
        }

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("file");

        // the directory to upload to
        String uploadDir = getServletContext().getRealPath("/resources") + "/" + request.getRemoteUser() + "/";

        // Create the directory if it doesn't exist
        File dirPath = new File(uploadDir);

        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }

        //retrieve the file data
        InputStream stream = file.getInputStream();

        //write the file to the file specified
        OutputStream bos = new FileOutputStream(uploadDir + file.getOriginalFilename());
        int bytesRead;
        byte[] buffer = new byte[8192];

        while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
            bos.write(buffer, 0, bytesRead);
        }

        bos.close();

        //close the stream
        stream.close();

        
        return getSuccessView();
    }
}
