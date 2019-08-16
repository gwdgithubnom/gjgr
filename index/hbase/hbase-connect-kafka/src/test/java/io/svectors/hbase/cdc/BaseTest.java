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

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HBaseTestingUtility;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.replication.ReplicationPeerConfig;
import org.apache.hadoop.hbase.zookeeper.ZKConfig;
import org.apache.hadoop.hbase.client.replication.ReplicationAdmin;
import org.apache.hadoop.hbase.replication.ReplicationException;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.junit.After;
import org.junit.Before;

import io.svectors.hbase.cdc.model.HRow;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author ravi.magham
 */
public abstract class BaseTest {

    protected HBaseTestingUtility utility;
    protected int numRegionServers;
    protected KafkaServer kafkaServer;

	@Before
    public void setUp() throws Exception {
        final Configuration hbaseConf = HBaseConfiguration.create();
		hbaseConf.setInt("replication.stats.thread.period.seconds", 5);
        hbaseConf.setLong("replication.sleep.before.failover", 2000);
        hbaseConf.setInt("replication.source.maxretriesmultiplier", 10);
        hbaseConf.setBoolean(HConstants.REPLICATION_ENABLE_KEY, true);

        // add kafka properties. we prefix each property with kafka
		addKafkaProperties(hbaseConf);

        utility = new HBaseTestingUtility(hbaseConf);
        utility.startMiniCluster();
        numRegionServers = utility.getHBaseCluster().getRegionServerThreads().size();

        // setup kafka
        kafkaServer = new KafkaServer(utility.getZkCluster().getClientPort(), 9092);

    }

	/**
	 * Add kafka properties to {@link Configuration}
	 * @param hbaseConf
	 */
	private void addKafkaProperties(Configuration hbaseConf) {
		hbaseConf.set("kafka.bootstrap.servers", "localhost:9092");
		hbaseConf.set("kafka.acks", "1");
		hbaseConf.set("kafka.producer.type", "async");
		hbaseConf.set("kafka.key.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");
		hbaseConf.set("kafka.value.serializer", "io.svectors.hbase.cdc.serde.HRowProtobufSerde");
	}

	/**
	 *
	 * @param configuration
	 * @param peerName
	 * @param tableCFs
	 * @throws ReplicationException
	 * @throws IOException
	 */
    protected void addPeer(final Configuration configuration,String peerName, Map<TableName, List<String>> tableCFs)
	      throws ReplicationException, IOException {
        try (ReplicationAdmin replicationAdmin = new ReplicationAdmin(configuration)) {
            ReplicationPeerConfig peerConfig = new ReplicationPeerConfig()
                .setClusterKey(ZKConfig.getZooKeeperClusterKey(configuration))
                .setReplicationEndpointImpl(HbaseEndpoint.class.getName());

            replicationAdmin.addPeer(peerName, peerConfig, tableCFs);
        }
    }

    @After
    public void tearDown() throws Exception {
		if(kafkaServer != null) {
			kafkaServer.shutdown();
        }
        if(utility != null) {
            utility.shutdownMiniCluster();
        }
    }

	/**
	 * Creates and get the Kafka consumer
	 * @return
	 */
	public KafkaConsumer<byte[], HRow> createAndGetKafkaConsumer() {
		final Properties props = new Properties();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer.getBrokerConnectionString());
		props.put("zookeeper.connect", kafkaServer.getZookeeperQuorum());
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "testing");
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
		props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.ByteArrayDeserializer");
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "io.svectors.hbase.cdc.serde.HRowProtobufSerde");
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		final KafkaConsumer<byte[], HRow> consumer = new KafkaConsumer<>(props);
		return consumer;
	}
}
