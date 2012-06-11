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
import org.openmrs.contrib.metadatarepository.service.FileException;
import org.openmrs.contrib.metadatarepository.service.PackageManager;
import org.openmrs.contrib.metadatarepository.service.impl.GenericManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
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
	
	 String packagelocation;
	 public String getPackagelocation() {
			return packagelocation;
		}

		public void setPackagelocation(String packagelocation) {
			this.packagelocation = packagelocation;
		}

	@Override
	public MetadataPackage save(MetadataPackage metadataPackage) {
		try{
		String filename = saveFile(metadataPackage.getFile());
		metadataPackage.setFilename(filename);
		
		}catch(IOException e){
		  
			throw new FileException("Failed to save the package on the disk");
		}
		return metadataPackage;
	}


	protected String saveFile(byte[] file) throws IOException{
		
		 // the directory to upload to
        String uploadDir = this.packagelocation;
        log.debug(file);
        // Create the directory if it doesn't exist
        File dirPath = new File(uploadDir);

        if (!dirPath.exists()) {
            dirPath.mkdir();
        }
        MetadataPackage m = new MetadataPackage();
       
         if(log.isDebugEnabled())
        	 log.debug("Saving file"+dirPath.toString());
              m =  dao.save(m);
              m.getId();
         String filename = m.getId().toString()+".zip";
        //write the file to the file specified
        File packagedata = new File(dirPath,filename);
        FileOutputStream bos;
       try{
        bos = new FileOutputStream(packagedata);
        /*log.debug(bos);
        log.debug("000000000000");
        log.debug(file.length);
        log.debug("!!!!!!!!!!");*/
        bos.write(file);
       }catch(IOException e){
    	   e.printStackTrace();
    	   throw new FileException("error writing a file");
       }
       if(bos!=null){
    	   try{
    		   bos.close();
    	   }catch(IOException e){
    		 //Close quietly not to hide the previous exception
    		   log.error(e);
    	   }
       }
        return filename;
     }
	
	
	
		
}
