/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.svectors.hbase.cdc;


/**
 *  @author ravi.magham
 */
public abstract class KafkaProps {

    public static final String KAFKA_WHITE_LIST_TABLES_PARAM = "kafka.hbase.tables.whitelist";
    public static final String KAFKA_PRODUCER_CALLBACK_HANDLER_PARAM = "kafka.callback.handler";
    public static final String KAFKA_PRODUCER_TYPE_PARAM = "kafka.producer.type";

    public static final String KAFKA_DEFAULT_CALLBACK_HANDLER = LoggerCallback.class.getName();
    public static final String KAFKA_ASYNC_PRODUCER_TYPE = "async";

  }
