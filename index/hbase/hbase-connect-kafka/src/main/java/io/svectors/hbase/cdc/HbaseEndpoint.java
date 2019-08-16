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

import static java.util.stream.Collectors.groupingBy;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import io.svectors.hbase.cdc.config.KafkaConfiguration;
import io.svectors.hbase.cdc.util.TopicNameFilter;
import io.svectors.hbase.cdc.func.ToHRowFunction;
import io.svectors.hbase.cdc.model.HRow;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.replication.BaseReplicationEndpoint;
import org.apache.hadoop.hbase.wal.WAL.Entry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  @author ravi.magham
 */
public class HbaseEndpoint extends BaseReplicationEndpoint {

    private static final Logger LOG = LoggerFactory.getLogger(HbaseEndpoint.class);

    private static final ToHRowFunction TO_HROW = new ToHRowFunction();
    private KafkaMessageProducer producer;
    private TopicNameFilter topicNameFilter;

    public HbaseEndpoint() {
        super();
    }

    @Override
    public void init(Context context) throws IOException {
        super.init(context);
		LOG.info("HbaseEndpoint init: ");
    }

    @Override
    public UUID getPeerUUID() {
        return UUID.randomUUID();
    }

    /**
     *
     * @param context
     * @return
     */
    @Override
    public boolean replicate(ReplicateContext context) {
        final List<Entry> entries = context.getEntries();

        final Map<String, List<Entry>> entriesByTable = entries.stream()
                          .filter(entry -> topicNameFilter.test(entry.getKey().getTablename().getNameAsString()))
                          .collect(groupingBy(entry -> entry.getKey().getTablename().getNameAsString()));

        // persist the data to kafka in parallel.
        entriesByTable.entrySet().stream().forEach(entry -> {
            final String tableName = entry.getKey();
            final List<Entry> tableEntries = entry.getValue();

            tableEntries.forEach(tblEntry -> {
	              List<Cell> cells = tblEntry.getEdit().getCells();

                // group the data by the rowkey.
	            Map<byte[], List<Cell>> columnsByRow = cells.stream()
	                  .collect(groupingBy(CellUtil::cloneRow));

	              // build the list of rows.
	            columnsByRow.entrySet().stream().forEach(rowcols -> {
	                final byte[] rowkey = rowcols.getKey();
	                  final List<Cell> columns = rowcols.getValue();
	                final HRow row = TO_HROW.apply(rowkey, columns);
	                producer.send(tableName, row);
	             });
            });
        });
        return true;
    }

    @Override
    protected void doStart() {
        LOG.info("Hbase replication to Kafka started at " + LocalDate.now());
        final Configuration hdfsConfig = ctx.getConfiguration();
        final KafkaConfiguration kafkaConfig = new KafkaConfiguration(hdfsConfig);
	    topicNameFilter = new TopicNameFilter(kafkaConfig);
        producer = KafkaProducerFactory.getInstance(kafkaConfig);
        notifyStarted();
    }

    @Override
    protected void doStop() {
        LOG.info("Hbase replication to Kafka stopped at " + LocalDate.now());
	    producer.close();
        notifyStopped();
    }
}
