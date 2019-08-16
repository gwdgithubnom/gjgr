/**
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
package io.svectors.hbase.cdc.config;

import io.svectors.hbase.cdc.ReplicationException;
import org.apache.hadoop.conf.Configuration;
import org.apache.kafka.clients.producer.Callback;
import java.util.Map;
import java.util.Optional;

import static io.svectors.hbase.cdc.KafkaProps.*;
import static java.util.stream.Collectors.toMap;

/**
 * @author ravi.magham
 */
public class KafkaConfiguration {

	public static final String KAFKA_PREFIX_KEY = "kafka.";
	private final Configuration configuration;
	private final Map<String,Object> kafkaProperties;

	/**
	 *
	 * @param configuration
	 */
	public KafkaConfiguration(final Configuration configuration) {
			this.configuration = configuration;
			this.kafkaProperties = filter(configuration);
	}

	/**
	 * Filters the configuration for
	 * @param configuration
	 * @return
	 */
	private Map<String,Object> filter(final Configuration configuration) {
			final Map<String,Object> kafkaProperties = configuration.getValByRegex(KAFKA_PREFIX_KEY)
				.entrySet()
				.stream()
				.collect(toMap(e -> e.getKey().substring(KAFKA_PREFIX_KEY.length()), e -> e.getValue()));
			return kafkaProperties;
	}

	/**
	 * Returns the properties for kafka.
	 * @return
	 */
	public Map<String,Object> getAsMap() {
			return kafkaProperties;
	}

	/**
	 * Returns the default callback handler when running in async mode.
	 * @return
	 */
	public Callback getCallbackHandler() {
		  try {
			    final String handlerClassName = this.configuration.get(KAFKA_PRODUCER_CALLBACK_HANDLER_PARAM,
				        KAFKA_DEFAULT_CALLBACK_HANDLER);
			    final Class<? extends Callback> handlerClass = (Class<? extends Callback>) Class.forName(handlerClassName);
			    return handlerClass.newInstance();
		  } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			    throw new ReplicationException(e);
		  }
	}

	/**
	 * returns if we are using the sync or async producer types.
	 * @return
	 */
	public boolean isAsyncProducer() {
			return KAFKA_ASYNC_PRODUCER_TYPE.equalsIgnoreCase(this.configuration.get(KAFKA_PRODUCER_TYPE_PARAM));
	}

	/**
	 * returns the whitelist topics if any mentioned in configuration.
	 * @return
	 */
	public Optional<String> getWhitelistTopics() {
			Object topicNames = this.configuration.get(KAFKA_WHITE_LIST_TABLES_PARAM);
			if(topicNames == null) {
				return Optional.empty();
			} else {
				return Optional.of(topicNames.toString());
			}
	}
}
