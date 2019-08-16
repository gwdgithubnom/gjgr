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

import com.google.common.base.Preconditions;
import kafka.server.KafkaConfig;
import kafka.server.KafkaServerStartable;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.function.Function;

/**
 * @author ravi.magham
 */
public class KafkaServer {

    private final int brokerPort;  // kafka broker port
	  private final int zookeeperPort; // zookeeper port
		private final KafkaServerStartable kafka;
		private final File logDir;

    private final Function<Integer,String> TO_LOCAL_URI = (port) -> "localhost:"+ port;

    public KafkaServer(int zookeeperPort, int kafkaBrokerPort) {
        try {
            Preconditions.checkArgument(zookeeperPort > 0);
            Preconditions.checkArgument(kafkaBrokerPort > 0);
            this.zookeeperPort = zookeeperPort;
            this.brokerPort = kafkaBrokerPort;
	          this.logDir = new File(System.getProperty("java.io.tmpdir"), "kafka/logs/hbase-cdc-kafka-" + brokerPort);

	          KafkaConfig config = buildKafkaConfig(zookeeperPort);
            kafka = new KafkaServerStartable(config);
            kafka.startup();
        } catch (Exception ex) {
            throw new RuntimeException("Could not start test broker", ex);
        }
    }

    private kafka.server.KafkaConfig buildKafkaConfig(int zookeeperPort) throws IOException {
        Properties p = new Properties();
        p.setProperty("zookeeper.connect", TO_LOCAL_URI.apply(zookeeperPort));
        p.setProperty("broker.id", "0");
        p.setProperty("port", Integer.toString(this.brokerPort));
        p.setProperty("log.dirs", logDir.getAbsolutePath());
        p.put("host.name", "localhost");
        p.put("controlled.shutdown.enable", "true");

        return new KafkaConfig(p);
    }

    public String getBrokerConnectionString() {
        return TO_LOCAL_URI.apply(this.brokerPort);
    }

    protected String getZookeeperQuorum() {
        return TO_LOCAL_URI.apply(zookeeperPort);
    }

    public void shutdown() {
        kafka.shutdown();
    }
}
