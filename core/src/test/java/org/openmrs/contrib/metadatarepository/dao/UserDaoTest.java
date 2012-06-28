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

import org.openmrs.contrib.metadatarepository.Constants;
import org.openmrs.contrib.metadatarepository.dao.RoleDao;
import org.openmrs.contrib.metadatarepository.dao.UserDao;
import org.openmrs.contrib.metadatarepository.model.Address;
import org.openmrs.contrib.metadatarepository.model.Role;
import org.openmrs.contrib.metadatarepository.model.User;
import org.compass.core.CompassCallbackWithoutResult;
import org.compass.core.CompassException;
import org.compass.core.CompassHits;
import org.compass.core.CompassSession;
import org.compass.core.CompassTemplate;
import org.compass.gps.CompassGps;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.ExpectedException;
import org.springframework.test.annotation.NotTransactional;

import static org.junit.Assert.*;

public class UserDaoTest extends BaseDaoTestCase {
    @Autowired
    private UserDao dao;
    @Autowired
    private RoleDao rdao;
    @Autowired
    private CompassTemplate compassTemplate;
    @Autowired
    private CompassGps compassGps;

    @Test
    @ExpectedException(DataAccessException.class)
    public void testGetUserInvalid() throws Exception {
        // should throw DataAccessException
        dao.get(1000L);
    }

    @Test
    public void testGetUser() throws Exception {
        User user = dao.get(1L);

        assertNotNull(user);
        assertEquals(1, user.getRoles().size());
        assertTrue(user.isEnabled());
    }

    @Test
    public void testGetUserPassword() throws Exception {
        User user = dao.get(1L);
        String password = dao.getUserPassword(user.getUsername());
        assertNotNull(password);
        log.debug("password: " + password);
    }

    @Test
    @NotTransactional
    @ExpectedException(DataIntegrityViolationException.class)
    public void testUpdateUser() throws Exception {
        User user = dao.get(1L);

        Address address = user.getAddress();
        address.setAddress("new address");

        dao.saveUser(user);
        flush();

        user = dao.get(1L);
        assertEquals(address, user.getAddress());
        assertEquals("new address", user.getAddress().getAddress());

        // verify that violation occurs when adding new user with same username
        user.setId(null);

        // should throw DataIntegrityViolationException
        dao.saveUser(user);
    }

    @Test
    public void testAddUserRole() throws Exception {
        User user = dao.get(1L);
        assertEquals(1, user.getRoles().size());

        Role role = rdao.getRoleByName(Constants.ADMIN_ROLE);
        user.addRole(role);
        dao.saveUser(user);
        flush();

        user = dao.get(1L);
        assertEquals(2, user.getRoles().size());

        //add the same role twice - should result in no additional role
        user.addRole(role);
        dao.saveUser(user);
        flush();

        user = dao.get(1L);
        assertEquals("more than 2 roles", 2, user.getRoles().size());

        user.getRoles().remove(role);
        dao.saveUser(user);
        flush();

        user = dao.get(1L);
        assertEquals(1, user.getRoles().size());
    }

    @Test
    @ExpectedException(DataAccessException.class)
    public void testAddAndRemoveUser() throws Exception {
        User user = new User("testuser");
        user.setPassword("testpass");
        user.setFirstName("Test");
        user.setLastName("Last");
        Address address = new Address();
        address.setCity("Denver");
        address.setProvince("CO");
        address.setCountry("USA");
        address.setPostalCode("80210");
        user.setAddress(address);
        user.setEmail("testuser@appfuse.org");
        user.setWebsite("http://raibledesigns.com");

        Role role = rdao.getRoleByName(Constants.USER_ROLE);
        assertNotNull(role.getId());
        user.addRole(role);

        user = dao.saveUser(user);
        flush();

        assertNotNull(user.getId());
        user = dao.get(user.getId());
        assertEquals("testpass", user.getPassword());

        dao.remove(user.getId());
        flush();

        // should throw DataAccessException
        dao.get(user.getId());
    }

    @Test
    public void testUserExists() throws Exception {
        boolean b = dao.exists(1L);
        assertTrue(b);
    }

    @Test
    public void testUserNotExists() throws Exception {
        boolean b = dao.exists(111L);
        assertFalse(b);
    }

    @Test
    public void testUserSearch() throws Exception {
        // reindex all the data
        compassGps.index();

        User user = compassTemplate.get(User.class, 2);
        assertNotNull(user);
        assertEquals("Matt", user.getFirstName());

        compassTemplate.execute(new CompassCallbackWithoutResult() {
            @Override
            protected void doInCompassWithoutResult(CompassSession compassSession) throws CompassException {
                CompassHits hits = compassSession.find("Matt");
                assertEquals(1, hits.length());
                assertEquals("Matt", ((User) hits.data(0)).getFirstName());
                assertEquals("Matt", hits.resource(0).getValue("firstName"));
            }
        });

        // test mirroring
        user = dao.get(2L);
        user.setFirstName("MattX");
        dao.saveUser(user);
        flush();

        // now verify it is reflected in the index
        user = compassTemplate.get(User.class, 2);
        assertNotNull(user);
        assertEquals("MattX", user.getFirstName());

        compassTemplate.execute(new CompassCallbackWithoutResult() {
            @Override
            protected void doInCompassWithoutResult(CompassSession compassSession) throws CompassException {
                CompassHits hits = compassSession.find("MattX");
                assertEquals(1, hits.length());
                assertEquals("MattX", ((User) hits.data(0)).getFirstName());
            }
        });
    }
}
