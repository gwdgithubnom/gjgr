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

import static io.svectors.hbase.cdc.metrics.MetricRegistry.Kafka.OVERALL_SEND_RATE;
import static io.svectors.hbase.cdc.metrics.MetricRegistry.Kafka.SEND_FAILURES;

import com.google.common.base.Preconditions;
import com.yammer.metrics.Metrics;
import com.yammer.metrics.core.Counter;
import com.yammer.metrics.core.Meter;
import io.svectors.hbase.cdc.config.KafkaConfiguration;
import io.svectors.hbase.cdc.model.HRow;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.Closeable;
import java.util.concurrent.TimeUnit;


/**
 * @author ravi.magham
 */
public final class KafkaMessageProducer implements Closeable {

    private static final Logger LOG = LoggerFactory.getLogger(KafkaMessageProducer.class);

    private final KafkaProducer<byte[], HRow> kafkaProducer;
    private final RetryPolicy retryPolicy = new BackpressureRetryPolicy();
    private final Meter eventsPerSec = Metrics.newMeter(OVERALL_SEND_RATE, "events", TimeUnit.SECONDS);
    private final Counter failures = Metrics.newCounter(SEND_FAILURES);
	private final boolean isAsync;
	private final Callback callbackHandler;


	/**
	 *
	 * @param kafkaConfiguration
	 */
    public KafkaMessageProducer(final KafkaConfiguration kafkaConfiguration) {
	      Preconditions.checkNotNull(kafkaConfiguration);
	      this.kafkaProducer = new KafkaProducer<>(kafkaConfiguration.getAsMap());
	      this.isAsync = kafkaConfiguration.isAsyncProducer();
	      this.callbackHandler = kafkaConfiguration.getCallbackHandler();
	  }

    /**
     *
     * @param tableName
     * @param row
     */
    public void send(final String tableName, final HRow row) {
        final ProducerRecord<byte[], HRow> record = new ProducerRecord<>(tableName, row.getRowKey(), row);
        if(this.isAsync) {
            sendAsync(record);
        } else {
            sendSync(record);
        }
    }

    /**
     * pushes the message asynchronously
     * @param record
     */
    private void sendAsync(ProducerRecord<byte[], HRow> record) {
        boolean retry = true;
        while(retry) {
            try {
                this.kafkaProducer.send(record, callbackHandler);
                eventsPerSec.mark(1);
	            break;
            } catch (RuntimeException e) {
                retry = retryPolicy.shouldRetry(e);
            } catch (Exception ex) {
                failures.inc();
                final String errorMsg = String.format("Failed to send the record to kafka topic [%s] ", record.topic());
                throw new ReplicationException(errorMsg, ex);
            }
        }

    }

    /**
     * pushes the message synchronously
     * @param record
     */
    private void sendSync(ProducerRecord<byte[], HRow> record) {
        boolean retry = true;
        while(retry) {
            try {
                final RecordMetadata metadata = this.kafkaProducer.send(record).get();
	            eventsPerSec.mark(1);
                if (LOG.isDebugEnabled()) {
                    LOG.debug(String.format(" published message to topic [%s] , partition [%s] and the next offset is [%s]",
                      metadata.topic(), metadata.partition(), metadata.offset()));
                }
	              break;
            } catch (RuntimeException e) {
                retry = retryPolicy.shouldRetry(e);
            } catch (Exception ex) {
                failures.inc();
	              final String errorMsg = String.format("Failed to send the record to kafka topic [%s] ", record.topic());
                throw new ReplicationException(errorMsg, ex);
            }
        }
    }

    @Override
    public void close() {
        if(this.kafkaProducer != null) {
            this.kafkaProducer.close();
        }
    }
}
