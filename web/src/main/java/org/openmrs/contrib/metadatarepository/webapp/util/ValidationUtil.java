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

package org.openmrs.contrib.metadatarepository.webapp.util;

import org.apache.commons.validator.Field;
import org.apache.commons.validator.GenericValidator;
import org.apache.commons.validator.ValidatorAction;
import org.apache.commons.validator.util.ValidatorUtils;
import org.springframework.validation.Errors;
import org.springmodules.validation.commons.FieldChecks;


/**
 * ValidationUtil Helper class for performing custom validations that
 * aren't already included in the core Commons Validator.
 *
 * <p>
 * <a href="ValidationUtil.java.html"><i>View Source</i></a>
 * </p>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public class ValidationUtil {
    //~ Methods ================================================================

    /**
     * Validates that two fields match.
     * @param bean
     * @param va
     * @param field
     * @param errors
     */
    public static boolean validateTwoFields(Object bean, ValidatorAction va,
                                            Field field, Errors errors) {
        String value =
            ValidatorUtils.getValueAsString(bean, field.getProperty());
        String sProperty2 = field.getVarValue("secondProperty");
        String value2 = ValidatorUtils.getValueAsString(bean, sProperty2);

        if (!GenericValidator.isBlankOrNull(value)) {
            try {
                if (!value.equals(value2)) {
                    FieldChecks.rejectValue(errors, field, va);
                    return false;
                }
            } catch (Exception e) {
                FieldChecks.rejectValue(errors, field, va);
                return false;
            }
        }

        return true;
    }
}
