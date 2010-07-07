/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package gov.nasa.jpl.oodt.cas.filemgr.repository;

//JDK imports
import gov.nasa.jpl.oodt.cas.metadata.util.PathUtils;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author mattmann
 * @author bfoster
 * @version $Revision$
 * 
 * <p>
 * A Factory class for creating {@link XMLRepositoryManager} objects.
 * </p>
 * 
 */
public class XMLRepositoryManagerFactory implements RepositoryManagerFactory {

    /* list of dir uris specifying file paths to productType directories */
    private List<String> productTypeDirList = null;

    /* our log stream */
    private static Logger LOG = Logger
            .getLogger(XMLRepositoryManagerFactory.class.getName());

    /**
     * <p>
     * Default Constructor
     * </p>.
     */
    public XMLRepositoryManagerFactory() {
        String productTypeDirUris = System
                .getProperty("gov.nasa.jpl.oodt.cas.filemgr.repositorymgr.dirs");

        if (productTypeDirUris != null) {
            productTypeDirUris = PathUtils
                    .replaceEnvVariables(productTypeDirUris);
            String[] dirUris = productTypeDirUris.split(",");
            productTypeDirList = Arrays.asList(dirUris);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nasa.jpl.oodt.cas.filemgr.repository.RepositoryManagerFactory#createRepositoryManager()
     */
    public RepositoryManager createRepositoryManager() {
        if (productTypeDirList != null) {
            RepositoryManager repositoryManager = null;
            try {
                repositoryManager = new XMLRepositoryManager(productTypeDirList);
            } catch (Exception ignore) {
            }

            return repositoryManager;
        } else {
            LOG
                    .log(
                            Level.WARNING,
                            "Cannot create XML Repository Manager: no product type dir uris specified: value: "
                                    + System
                                            .getProperty("gov.nasa.jpl.oodt.cas.filemgr.repositorymgr.dirs"));
            return null;
        }
    }

}