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

import com.google.common.base.Throwables;
import com.yammer.metrics.Metrics;
import com.yammer.metrics.core.Counter;
import kafka.common.QueueFullException;
import org.apache.kafka.clients.producer.BufferExhaustedException;

import java.time.Duration;

import static io.svectors.hbase.cdc.metrics.MetricRegistry.Kafka.SEND_RETRIES;

/**
 * @author ravi.magham
 */
public class BackpressureRetryPolicy implements RetryPolicy {

	private final Duration retryInterval = Duration.ofMillis(1000);
	protected final Counter retries = Metrics.newCounter(SEND_RETRIES);


	@Override
	public boolean shouldRetry(RuntimeException e) {
		if(e instanceof BufferExhaustedException ||
			 e instanceof QueueFullException) {
			// kind of applying back pressure as we make the current thread to sleep.
			try {
				Thread.sleep(retryInterval.toMillis());
				retries.inc();
			} catch (InterruptedException ex) {
				throw Throwables.propagate(ex);
			}
			return true;
		} else {
			return false;
		}
	}
}
