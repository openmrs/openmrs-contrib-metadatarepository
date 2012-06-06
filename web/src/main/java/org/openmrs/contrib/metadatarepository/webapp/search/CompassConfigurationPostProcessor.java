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
 
package org.openmrs.contrib.metadatarepository.webapp.search;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.compass.core.config.CompassConfiguration;
import org.compass.core.config.ConfigurationException;
import org.compass.spring.LocalCompassBeanPostProcessor;

/**
 * Compass Post Processor that allows for adding scan mappings for more than
 * one root package.
 */
public class CompassConfigurationPostProcessor implements LocalCompassBeanPostProcessor {
    Log log = LogFactory.getLog(CompassConfigurationPostProcessor.class);

    public void process(CompassConfiguration config) throws ConfigurationException {
        // Look at current class's package and add it if it's not the AppFuse default
        String classPackage = this.getClass().getPackage().getName();
        String rootPackage = classPackage.substring(0, classPackage.indexOf("webapp") - 1);
        if (!rootPackage.equals("org.openmrs.contrib")) {
            log.debug("Adding scan for package: " + rootPackage);
            config.addScan(rootPackage);
        }
    }
}
