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
package io.svectors.hbase.cdc;

import com.yammer.metrics.Metrics;
import com.yammer.metrics.core.Counter;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.svectors.hbase.cdc.metrics.MetricRegistry.Kafka.ASYNC_SEND_FAILURES;

/**
 * @author ravi.magham
 */
public class LoggerCallback implements Callback {

	private static final Logger LOG = LoggerFactory.getLogger(LoggerCallback.class);
	private final Counter asyncFailures = Metrics.newCounter(ASYNC_SEND_FAILURES);

	@Override
	public void onCompletion(RecordMetadata recordMetadata, Exception e) {
			if (e != null) {
					asyncFailures.inc();
					LOG.error(String.format("Exception [%s] producing to topic ", e.getMessage()));
			} else {
					LOG.info(String.format(" the topic [%s] offset is [%s] ", recordMetadata.topic(), recordMetadata.offset()));
			}
	}
}
