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


package org.apache.oodt.cas.workflow.metadata;

/**
 * @author mattmann
 * @version $Revision$
 * 
 * <p>
 * Core metadata key names for the Workflow Manager
 * </p>.
 */
public interface CoreMetKeys {

    public static final String TASK_ID = "TaskId";

    public static final String WORKFLOW_INST_ID = "WorkflowInstId";

    public static final String JOB_ID = "JobId";

    public static final String PROCESSING_NODE = "ProcessingNode";

    public static final String WORKFLOW_MANAGER_URL = "WorkflowManagerUrl";

    public static final String QUEUE_NAME = "QueueName";
    
    public static final String TASK_LOAD = "TaskLoad";

}