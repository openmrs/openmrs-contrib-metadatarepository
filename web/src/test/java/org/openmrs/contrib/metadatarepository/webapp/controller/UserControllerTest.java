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

import org.openmrs.contrib.metadatarepository.Constants;
import org.openmrs.contrib.metadatarepository.webapp.controller.UserController;
import org.compass.gps.CompassGps;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class UserControllerTest extends BaseControllerTestCase {
    @Autowired
    private CompassGps compassGps;
    @Autowired
    private UserController c;

    @Test
    public void testHandleRequest() throws Exception {
        ModelAndView mav = c.handleRequest(null);
        Map m = mav.getModel();
        assertNotNull(m.get(Constants.USER_LIST));
        assertEquals("admin/userList", mav.getViewName());
    }

    @Test
    public void testSearch() throws Exception {
        compassGps.index();
        ModelAndView mav = c.handleRequest("admin");
        Map m = mav.getModel();
        List results = (List) m.get(Constants.USER_LIST);
        assertNotNull(results);
        assertTrue(results.size() >= 1);
        assertEquals("admin/userList", mav.getViewName());
    }
}
