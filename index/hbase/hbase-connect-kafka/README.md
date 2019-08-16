# hbase-connect-kafka

Publish changes of HBase tables to Kafka . 

## Pre-requisites

* HBase 1.2.0
* JDK 1.8
* Kafka 0.9

## How it works

[HBaseEndpoint](https://github.com/mravi/hbase-cdc-kafka/blob/master/src/main/java/io/svectors/hbase/cdc/HbaseEndpoint.java) is a custom
replication end point that receives all mutations( Put / Delete). Based on the white list topics specified in the hbase-site.xml, the events are
filtered and mirrored to Kafka using the Kafka producer api.

## Assumptions

* Each HBase table is mapped to a Kafka topic.
* HBase cluster is configured with the setting hbase.replication to true in hbase-site.xml

## Properties

Have the below properties set in hbase-site.xml and add it to the HBase region server classpath.
Each kafka producer property should be prefixed with `kafka`.

name | data type | required | description
-----|-----------|----------|------------
kafka.bootstrap.servers | string | yes | Kafka broker servers.
kafka.producer.type | string | no | Can be either sync or async. Default `sync`


## Packaging

* mvn clean package


## Deployment

* Add hbase-cdc-kafka.jar and hbase-site.xml with the required properties to all the HBase Region servers classpath and restart them.

* At HBase shell, run the following commands.

```bash
hbase> create 'test', {NAME => 'd', REPLICATION_SCOPE => '1'}
hbase> add_peer 'kafka-repl', ENDPOINT_CLASSNAME 'io.svectors.hbase.cdc.HbaseEndpoint'
hbase> put 'test', 'r1', 'd', 'value'
```

## TODO

* Write Avro and Json Serializers for HRow.
* Publish yammer metrics.
