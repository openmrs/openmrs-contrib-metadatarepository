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

import org.openmrs.contrib.metadatarepository.model.Role;

import java.util.List;

/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 *
 * @author <a href="mailto:dan@getrolling.com">Dan Kibler </a>
 */
public interface RoleManager extends GenericManager<Role, Long> {
    /**
     * {@inheritDoc}
     */
    List getRoles(Role role);

    /**
     * {@inheritDoc}
     */
    Role getRole(String rolename);

    /**
     * {@inheritDoc}
     */
    Role saveRole(Role role);

    /**
     * {@inheritDoc}
     */
    void removeRole(String rolename);
}
