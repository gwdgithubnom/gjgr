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
package io.svectors.hbase.cdc.metrics;

import com.yammer.metrics.core.MetricName;

/**
 * @author ravi.magham
 */
public abstract class MetricRegistry {

    public static final String GROUP = "hbase-cdc-kafka";

    private MetricRegistry(){}

    public static abstract class Hbase {
        private Hbase() {
        }
        private static final String TYPE = "hbase";

        public static final MetricName OVERALL_RECEIVE_RATE =
            new MetricName(GROUP, TYPE, "Overall receive rate");

        public static final MetricName OVERALL_SEND_RATE =
            new MetricName(GROUP, TYPE, "Overall send rate");

        public static final MetricName DROPPED_MUTATIONS =
            new MetricName(GROUP, TYPE, "mutations that were dropped by HBase");

    }

    public static abstract class Kafka {
        private Kafka() {
        }
        private static final String TYPE = "kafka";

        public static final MetricName OVERALL_SEND_RATE =
            new MetricName(GROUP, TYPE, "Overall Send Rate");

        public static final MetricName SEND_FAILURES =
            new MetricName(GROUP, TYPE, "Failures to send events to kafka");

        public static final MetricName SEND_RETRIES =
            new MetricName(GROUP, TYPE, "Retries to send events to kafka");

        public static final MetricName ASYNC_SEND_FAILURES =
            new MetricName(GROUP, TYPE, "Failures to send events to kafka in async mode");

    }
}
