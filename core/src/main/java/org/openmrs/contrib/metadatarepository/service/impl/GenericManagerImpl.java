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
 
package org.openmrs.contrib.metadatarepository.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.contrib.metadatarepository.dao.GenericDao;
import org.openmrs.contrib.metadatarepository.model.User;
import org.openmrs.contrib.metadatarepository.service.GenericManager;
import org.compass.core.Compass;
import org.compass.core.CompassHit;
import org.compass.core.support.search.CompassSearchCommand;
import org.compass.core.support.search.CompassSearchHelper;
import org.compass.core.support.search.CompassSearchResults;
import org.compass.core.support.search.CompassSearchResults.Page;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class serves as the Base class for all other Managers - namely to hold
 * common CRUD methods that they might all use. You should only need to extend
 * this class when your require custom CRUD logic.
 * <p/>
 * <p>To register this class in your Spring context file, use the following XML.
 * <pre>
 *     &lt;bean id="userManager" class="org.openmrs.contrib.metadatarepository.service.impl.GenericManagerImpl"&gt;
 *         &lt;constructor-arg&gt;
 *             &lt;bean class="org.openmrs.contrib.metadatarepository.dao.hibernate.GenericDaoHibernate"&gt;
 *                 &lt;constructor-arg value="org.openmrs.contrib.metadatarepository.model.User"/&gt;
 *                 &lt;property name="sessionFactory" ref="sessionFactory"/&gt;
 *             &lt;/bean&gt;
 *         &lt;/constructor-arg&gt;
 *     &lt;/bean&gt;
 * </pre>
 * <p/>
 * <p>If you're using iBATIS instead of Hibernate, use:
 * <pre>
 *     &lt;bean id="userManager" class="org.openmrs.contrib.metadatarepository.service.impl.GenericManagerImpl"&gt;
 *         &lt;constructor-arg&gt;
 *             &lt;bean class="org.openmrs.contrib.metadatarepository.dao.ibatis.GenericDaoiBatis"&gt;
 *                 &lt;constructor-arg value="org.openmrs.contrib.metadatarepository.model.User"/&gt;
 *                 &lt;property name="dataSource" ref="dataSource"/&gt;
 *                 &lt;property name="sqlMapClient" ref="sqlMapClient"/&gt;
 *             &lt;/bean&gt;
 *         &lt;/constructor-arg&gt;
 *     &lt;/bean&gt;
 * </pre>
 *
 * @param <T>  a type variable
 * @param <PK> the primary key for that type
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public class GenericManagerImpl<T, PK extends Serializable> implements GenericManager<T, PK> {
    /**
     * Log variable for all child classes. Uses LogFactory.getLog(getClass()) from Commons Logging
     */
    protected final Log log = LogFactory.getLog(getClass());

    /**
     * GenericDao instance, set by constructor of child classes
     */
    protected GenericDao<T, PK> dao;

   /* @Autowired
    private CompassSearchHelper compass;*/
    @Autowired
    private Compass compass;
    
    /**
     * Sets the page size for the pagination of the results. If not set, not
     * pagination will be used.
     */
    public Long getPageSize() {
		return pageSize;
	}

    /**
     * Returns the page size for the pagination of the results. If not set, not
     * pagination will be used.
     * 
     * @param pageSize
     *            The page size when using paginated results
     */
	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
	}

	private Long pageSize;

    public GenericManagerImpl() {
    }

    public GenericManagerImpl(GenericDao<T, PK> genericDao) {
        this.dao = genericDao;
    }

    /**
     * {@inheritDoc}
     */
    public List<T> getAll() {
        return dao.getAll();
    }

    /**
     * {@inheritDoc}
     */
    public T get(PK id) {
        return dao.get(id);
    }

    /**
     * {@inheritDoc}
     */
    public boolean exists(PK id) {
        return dao.exists(id);
    }

    /**
     * {@inheritDoc}
     */
    public T save(T object) {
        return dao.save(object);
    }

    /**
     * {@inheritDoc}
     */
    public void remove(PK id) {
        dao.remove(id);
    }

    /**
     * {@inheritDoc}
     * <p/>
     * Search implementation using Compass.
     */
    @SuppressWarnings("unchecked")
    public List<T> search(String q, Class clazz,Long pageSize) {
        if (q == null || "".equals(q.trim())) {
            return getAll();
        }

        List<T> results = new ArrayList<T>();

        CompassSearchCommand command = new CompassSearchCommand(q);
      
        CompassSearchHelper searchHelper = new CompassSearchHelper(compass, 25);
        CompassSearchResults compassResults = searchHelper.search(new CompassSearchCommand(q, new Integer(0)));
        for (int i = 0; i < compassResults.getHits().length; i++) {
        	  CompassHit hit = compassResults.getHits()[i];
        	  if (clazz != null) {
                  if (hit.data().getClass().equals(clazz)) {
                      results.add((T) hit.data());
                  }
              } else {
                  results.add((T) hit.data());
              }
        	log.debug("Results size is "+results.size());
        	}

        // iterate through the search results pages
        for (int i = 0; i < compassResults.getPages().length; i++) {
          Page pages = compassResults.getPages()[i];
          log.debug("page size is "+pages.getSize());
          
        }

        if (log.isDebugEnabled() && clazz != null) {
            log.debug("Filtering by type: " + clazz.getName());
        }


        if (log.isDebugEnabled()) {
            log.debug("Number of results for '" + q + "': " + results.size());
        }

        return results;
    }

	
}
