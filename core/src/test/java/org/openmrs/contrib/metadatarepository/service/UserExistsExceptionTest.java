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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.contrib.metadatarepository.model.User;
import org.openmrs.contrib.metadatarepository.service.UserExistsException;
import org.openmrs.contrib.metadatarepository.service.UserManager;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.ExpectedException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import static org.junit.Assert.assertNotNull;

@ContextConfiguration(
    locations={"/applicationContext-service.xml",
               "/applicationContext-resources.xml",
               "classpath:/applicationContext-dao.xml"})
public class UserExistsExceptionTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private UserManager manager;
    private Log log = LogFactory.getLog(UserExistsExceptionTest.class);

    @Test
    @ExpectedException(UserExistsException.class)
    public void testAddExistingUser() throws Exception {
        log.debug("entered 'testAddExistingUser' method");
        assertNotNull(manager);

        User user = manager.getUser("-1");
        
        // create new object with null id - Hibernate doesn't like setId(null)
        User user2 = new User();
        BeanUtils.copyProperties(user, user2);
        user2.setId(null);
        user2.setVersion(null);
        user2.setRoles(null);
        
        // try saving as new user, this should fail UserExistsException b/c of unique keys
        manager.saveUser(user2);
    }    
}
