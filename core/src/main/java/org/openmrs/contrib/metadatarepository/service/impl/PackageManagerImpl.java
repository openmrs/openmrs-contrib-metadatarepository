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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.openmrs.contrib.metadatarepository.dao.PackageDao;
import org.openmrs.contrib.metadatarepository.model.MetadataPackage;
import org.openmrs.contrib.metadatarepository.service.PackageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Implementation of PackageManager interface.
 */
@Service("packageManager")
public class PackageManagerImpl extends GenericManagerImpl<MetadataPackage, Long> implements PackageManager {

    PackageDao packageDao;
	@Autowired
	public void setPackageDao(PackageDao packageDao) {
		this.dao=packageDao;
		this.packageDao=packageDao;
	}
	
	
	@Override
	public MetadataPackage save(MetadataPackage metadataPackage) {
		try{
		saveFile(metadataPackage.getFile());
		}catch(IOException e){
		  log.warn(e.getMessage());
		}
		return super.save(metadataPackage);
	}


	protected void saveFile(byte[] file) throws IOException{
		
		 // the directory to upload to
        String uploadDir ="/resources";

        // Create the directory if it doesn't exist
        File dirPath = new File(uploadDir);

        if (!dirPath.exists()) {
            dirPath.mkdir();
        }

      System.out.println(dirPath.toString());
        //write the file to the file specified
       // File test = new File(dirPath,"zerocool.txt");
        FileOutputStream bos;
       try{
        bos = new FileOutputStream(uploadDir);
        bos.write(file);
        bos.flush();
        bos.close();
       }catch(IOException e){
    	   log.warn(e.getMessage());
       }
        
     }
	
	
	
		
}
