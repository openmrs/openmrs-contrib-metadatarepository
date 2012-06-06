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


package org.openmrs.contrib.metadatarepository.dao.hibernate;

import org.hibernate.SessionFactory;
import org.openmrs.contrib.metadatarepository.dao.PackageDao;
import org.openmrs.contrib.metadatarepository.model.Package;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;

@Repository("packageDao")
public class PackageDaoHibernate extends GenericDaoHibernate<Package, Long> implements PackageDao {

	/**
     * Constructor that sets the entity to Package.class.
     */
    public PackageDaoHibernate() {
        super(Package.class);
    }
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Package> getPackages() {
        return getHibernateTemplate().find("from Package p order by upper(p.name)");
    }

    /**
     * {@inheritDoc}
     */
    public Package getPackageByName(String pkgname) {
        List packages = getHibernateTemplate().find("from Packages where name=?", pkgname);
        if (packages.isEmpty()) {
            return null;
        } else {
            return (Package) packages.get(0);
        }
    }
	
	/**
     * {@inheritDoc}
     */
    public void removePackage(String pkgname) {
        Object pkg = getPackageByName(pkgname);
        getHibernateTemplate().delete(pkg);
    }
	

}
