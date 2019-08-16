# Hadoop

## Hadoop分布式存储文件系统

  -（硬件故障是常态，而非偶然）自动快速检测应对硬件错误

  -流式访问数据（数据批处理）

  -转移计算比移动数据本身更划算（减少数据传输）

  -简单的数据一致性模型（一次写入，多次读取的文件访问模型）

  -异构平台可移植

## 安装
### 1. 目标

- 热备份（多Namenode节点，实现Standby与Ready自动切换）
- 冷备份（使用手动备份数据或者使用脚本备份到NFS或者固定节点上上）

### 2. HDFS体系结构

主从服务结构，采用Master-Slaver模式：

NameNode中心服务器（Master）、DataNode分布在不同的机架上（Slaver）、其他服务非核心服务（但提高服务稳定性）。

Namenode节点主要负责维护文件系统树、以及整棵树内的文件目录、存储元数据；负责整个数据集群的管理,如FSImage,editLog文件的操作。在数据节点方面，客户端或者NameNode的调度下，存储并检索数据块，并且定期向NameNode发送所存储的数据块的列表。对于数据的存储策略上，默认情况下，每个DataNode都保存了3个副本，其中两个保存在同一个机架的两个不同的节点上。另一个副本放在不同机架上的节点上。

### 3. 基本概念

机架，集中式的管理多台服务器。在HDFS集群中，大量DataNode会分布在不同的机架上组成，不同机架之间节点通过交换机通信，HDFS通过机架感知策略，使NameNode能够确定每个DataNode所属的机架ID，使用副本存放策略，来改进数据的可靠性、可用性和网络带宽的利用率（参考林子雨大数据教程）。

数据块(block)是HDFS最基本的存储单元，在配置的默认大小为64M，用户可以自行设置大小，可以根据具体的业务需求做出具体的更改。元数据包括HDFS文件系统中，文件和目录的属性信息。HDFS实现时，采用了 镜像文件（Fsimage） + 日志文件（EditLog）的备份机制。文件的镜像文件中内容包括：修改时间、访问时间、数据块大小、组成文件的数据块的存储位置信息。目录的镜像文件内容包括：修改时间、访问控制权限等信息。日志文件记录的是：HDFS的更新操作。NameNode启动的时候，会将镜像文件和日志文件的内容在内存中合并。把内存中的元数据更新到最新状态。HDFS存储的大部分数据都是用户数据，会以数据块的形式存放在DataNode上。

在HDFS中，NameNode 和 DataNode之间使用TCP协议进行通信。DataNode每3s向NameNode发送一个心跳。每10次心跳后，向NameNode发送一个数据块报告自己的信息，通过这些信息，NameNode能够重建元数据，并确保每个数据块有足够的副本。

　　本文是采用HDFS High Availability Using the Quorum Journal Manager 方案来实现HA，参考文章[HDFS High Availability Using the Quorum Journal Manager](http://hadoop.apache.org/docs/r2.7.4/hadoop-project-dist/hadoop-hdfs/HDFSHighAvailabilityWithQJM.html)。通过HA模式，我们可以实现namenode单点故障自动切换的功能，当然这方面必须有zookeeper集群来实现支持。

　　对于Hadoop集群的总体分布，我们可以划分三个类别的集群，分别是Hadoop核心组件集群、Zookeeper集群、HBase集群。如下表所示我们提供的每个机器所搭载服务的分配情况。

|  编号  |   域名    |    类型     | NameNode | JournalNode | DataNode | Nodemanager | Zookeeper |
| :--: | :-----: | :-------: | :------: | :---------: | :------: | :---------: | :-------: |
|  1   |  black  |  Hadoop   |   :o:    |     :x:     |   :x:    |     :x:     |    :x:    |
|  2   | black0  |  Hadoop   |   :o:    |     :x:     |   :x:    |     :x:     |    :o:    |
|  3   | black1  |  Hadoop   |   :x:    |     :x:     |   :o:    |     :o:     |    :x:    |
|  4   | black2  |  Hadoop   |   :x:    |     :x:     |   :o:    |     :x:     |    :o:    |
|  5   | black3  |  Hadoop   |   :x:    |     :x:     |   :o:    |     :o:     |           |
|  6   | black4  |  Hadoop   |   :x:    |     :x:     |   :o:    |     :x:     |    :o:    |
|  7   | black5  |  Hadoop   |   :x:    |     :x:     |   :o:    |     :o:     |    :x:    |
|  8   | black6  |  Hadoop   |   :x:    |     :o:     |   :x:    |     :x:     |    :x:    |
|  9   | black7  |  Hadoop   |   :x:    |     :o:     |   :x:    |     :x:     |    :x:    |
|  10  | black8  |  Hadoop   |   :x:    |     :o:     |   :x:    |     :x:     |    :x:    |
|  11  | black9  | Zookeeper |   :x:    |     :x:     |   :x:    |     :x:     |    :o:    |
|  12  | black10 | Zookeeper |   :x:    |     :x:     |   :x:    |     :x:     |    :o:    |
|  13  | black11 | Zookeeper |   :x:    |     :x:     |   :x:    |     :x:     |    :o:    |
|  14  | black12 | Zookeeper |   :x:    |     :x:     |   :x:    |     :x:     |    :o:    |



|  编号  |   域名    |    类型     | Tasktracker | JobTracker | ResourceManager |
| :--: | :-----: | :-------: | :---------: | :--------: | :-------------: |
|  8   | black6  | MapReduce |     :o:     |    :x:     |       :x:       |
|  9   | black7  | MapReduce |     :o:     |    :x:     |       :x:       |
|  10  | black8  | MapReduce |     :o:     |    :x:     |       :x:       |
|  15  | black13 | MapReduce |     :x:     |    :o:     |       :o:       |



|  编号  |   域名    |  类型   | HMaster | Hregionserver | Zookeeper |
| :--: | :-----: | :---: | :-----: | :-----------: | :-------: |
|  16  | black14 | HBase |   :o:   |      :x:      |    :x:    |
|  17  | black15 | HBase |   :o:   |      :x:      |    :x:    |
|  18  | black16 | HBase |   :x:   |      :o:      |    :o:    |
|  19  | black17 | HBase |   :x:   |      :o:      |    :o:    |
|  20  | black18 | HBase |   :x:   |      :o:      |    :o:    |
|  21  | black19 | HBase |   :x:   |      :o:      |    :o:    |
|  22  | black20 | HBase |   :x:   |      :o:      |    :o:    |



|  编号  |   域名   |  类型   | Kafka | Zookeeper | ResourceManager |
| :--: | :----: | :---: | :---: | :-------: | :-------------: |
|  23  | white1 | Kafka |  :o:  |    :x:    |       :x:       |
|  24  | white2 | Kafka |  :o:  |    :x:    |       :x:       |
|  25  | white3 | Kafka |  :o:  |    :x:    |       :x:       |
|  26  | white4 |       |  :x:  |    :o:    |       :o:       |



|  编号  |  域名   |  类型   | Redis |      |      |
| :--: | :---: | :---: | :---: | :--: | :--: |
|  27  | gray1 | Redis |  :o:  | :x:  | :x:  |
|  28  | gray2 | Redis |  :o:  | :x:  | :x:  |
|  29  | gray3 | Redis |  :o:  | :x:  | :x:  |
|  30  | gray4 |       |  :x:  | :o:  | :o:  |

### 4. zookeeper集群

　　对于集群，搭建假设所有机器具备SSH免登陆模块。对于该集群的安装可以参考[Zookeeper Get Start](http://zookeeper.apache.org/doc/r3.4.9/zookeeperStarted.html)。这篇文章详细介绍了zookeeper的使用与安装。在我们的方案中，使用的是多个Zookeeper集群，因为没有确定是否可以使用一个集群的局部节点，所以就直接将提出使用多个集群。这样一方面避免集群之间的依赖性台高，避免后期集群升级或者集群添加组件而影响个别服务的运行。主要配置两个文件，一个是zoo.cfg，而另外一个是my.id。

``` cfg
# 建议可以可以适量增大，可以缓解Zookeeper超时，保持合适的一次心跳时间
tickTime=2000
# 定义法定人数中的ZooKeeper服务器连接领导者的超时次数
initLimit=10
# 定义服务器从一个领导者的过期时间
syncLimit=5
# the directory where the snapshot is stored.
# dataDir=/home/gwd/data/zookeeper
# the port at which the clients will connect
clientPort=2181
# 设置集群最大连接数
#maxClientCnxns=60
# 当autopurge.purgeIntervale设置为0，表示开启自动清理功能。
autopurge.purgeInterval=1
# 可以设置每7*24小时，就进行自动清理
#autopurge.snapRetainCount=3
# 配置Zookeeper的存储资源路径
dataDir=/home/gwd/data/zookeeper/data
# 配置Zookeeper的日志路径
dataLogDir=/home/gwd/data/zookeeper/logs
# 配置集群服务的所有节点server.id=domain:port1:port2
# port1集群内部进行主节点与从节点消息通信端口，port2集群选择主节点端口
server.1024=black.machine.nom:2888:3888
server.101=black1.machine.nom:2888:3888
server.102=black2.machine.nom:2888:3888
server.103=black3:2888:3888
server.104=black4:2888:3888
server.105=black5:2888:3888
server.106=black6:2888:3888
#server.107=black7:2888:3888
#server.200=white:2888:2888
server.151=gray1:2888:3888
#server.153=gray1:2999:3999
server.152=gray2:2888:3888
#server.154=gray2:2999:3999
```

对上述配置文件，在同一个Zookeeper集群配置文件一样。在dataDir目录中myid文件则对应写为server.xxx中对应的数值，必须为数字。

### 5. Hadoop集群

　通过[文章](http://hadoop.apache.org/docs/r2.7.4/hadoop-project-dist/hadoop-hdfs/HDFSHighAvailabilityWithQJM.html)提到的方法，在2.x中，使用Quorum Journal Manager对Namenode进行数据的备份与共享。在HA模式下，比一个Namenode加SecondNamenode方式更加方便实现热部署。在Hadoop中的集群文件配置主要包括三个文件core-site.xml，hdfs.xml，mapreduce.xml.

#### 5.1 配置core-site.xml文件

``` xml
    <?xml version="1.0" encoding="UTF-8"?>
    <?xml-stylesheet type="text/xsl" href="configuration.xsl"?>
    <!--
      Licensed under the Apache License, Version 2.0 (the "License");
      you may not use this file except in compliance with the License.
      You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
      Unless required by  applicable law or agreed to in writing, software
      distributed under the License is distributed on an "AS IS" BASIS,
      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
      See the License for the specific language governing permissions and
      limitations under the License. See accompanying LICENSE file.
    -->
    <!-- Put site-specific property overrides in this file. -->
   <configuration>
     <!--配置Hadoop访问地址-->
    <property>
        <name>fs.defaultFS</name>
        <value>hdfs://hadoop/</value>
    </property>
    <property>
        <name>dfs.permissions</name>
        <value>false</value>
    </property>
     <!--配置数据存储目录-->
    <property>
        <name>hadoop.tmp.dir</name>
        <value>/home/gwd/data/hadoop/tmp</value>
    </property>
    <!-- zookeeper:white4,black0,black2,black4,black9,black10,black11,black12
-->
     <!--配置Zookeeper集群-->
    <property>
        <name>ha.zookeeper.quorum</name>
        <value>white4:2181,black0:2181,black2:2181,black9:2181,black4:2181,black11:2181,black12:2181</value>
    </property>
     <!--配置超时连接时间-->
    <property>
        <name>ipc.client.connect.retry.interval</name>
        <value>10000</value>
    </property>
</configuration>
```

#### 5.2 配置hdfs-site.xml文件
```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <?xml-stylesheet type="text/xsl" href="configuration.xsl"?>
    <!--
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License. See accompanying LICENSE file.
    -->
    <configuration>
        <property>
            <name>dfs.nameservices</name>
            <value>hadoop</value>
        </property>
        <!--配置集群namenode地址-->
        <property>
            <name>dfs.ha.namenodes.hadoop</name>
            <value>namenode1,namenode2</value>
        </property>
        <!-- namenode:black,black0 -->
        <!--配置Namenode1的rpc调用地址与端口-->
        <property>
            <name>dfs.namenode.rpc-address.hadoop.namenode1</name>
            <value>black:9000</value>
        </property>
        <property>
            <name>dfs.namenode.servicerpc-address.hadoop.namenode1</name>
            <value>black:9001</value>
        </property>
        <property>
            <name>df.namenode-http-address.hadoop.namenode1</name>
            <value>black:50070</value>
        </property>
          <!--配置Namenode2的rpc调用地址与端口-->
        <property>
            <name>dfs.namenode.rpc-address.hadoop.namenode2</name>
            <value>black0:9000</value>
        </property>
        <property>
            <name>dfs.namenode.servicerpc-address.hadoop.namenode2</name>
            <value>black0:9001</value>
        </property>
        <property>
            <name>df.namenode-http-address.hadoop.namenode2</name>
            <value>black0:50070</value>
        </property>
        <!--
           journalnode:black6,black7,black8
         -->
       <!--配置journalNode地址与IP，默认8485端口-->
        <property>
            <name>dfs.namenode.shared.edits.dir</name>
            <value>qjournal://black7:8485;black8:8485;black6:8485/hadoop</value>
        </property>
        <!--设置是否自恢复-->
        <property>
            <name>dfs.ha.automatic-failover-enabled.hadoop</name>
            <value>true</value>
        </property>
        <property>
            <name>dfs.ha.automatic-failover.enabled</name>
            <value>true</value>
        </property>
        <property>
            <name>dfs.client.failover.proxy.provider.hadoop</name>
            <value>org.apache.hadoop.hdfs.server.namenode.ha.ConfiguredFailoverProxyProvider</value>
        </property>
        <!--配置自恢复方式，使用ssh方法-->
        <property>
            <name>dfs.ha.fencing.methods</name>
            <value>sshfence</value>
        </property>
        <!--配置免ssh认证，秘钥-->
        <property>
            <name>dfs.ha.fencing.ssh.private-key-files</name>
            <value>/root/.ssh/id_rsa</value>
        </property>
        <property>
            <name>dfs.webhdfs.enabled</name>
            <value>true</value>
        </property>
        <!--配置默认备份个数-->
        <property>
            <name>dfs.replication</name>
            <value>3</value>
        </property>
        <!--自定义配置Hadoop存储目录-->
        <property>
            <name>dfs.namenode.name.dir</name>
            <value>file://${hadoop.tmp.dir}/../name</value>
        </property>
        <property>
            <name>dfs.datanode.data.dir</name>
            <value>file://${hadoop.tmp.dir}/../data</value>
            <final>true</final>
        </property>
        <property>
            <name>dfs.journalnode.edits.dir</name>
            <value>${hadoop.tmp.dir}/../journal</value>
        </property>
    </configuration>
```
如果出现ssh连接失败，就使用如下配置：
``` xml
    <property>
      <name>dfs.ha.fencing.methods</name>
      <value>shell(/bin/true)</value>
    </property>
```
#### 5.3 配置Yarn-site.xml文件

该文件的配置对于不同的机器需要做出相应的修改工作。

``` xml
    <?xml version="1.0"?>
    <!--
      Licensed under the Apache License, Version 2.0 (the "License");
      you may not use this file except in compliance with the License.
      You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

      Unless required by applicable law or agreed to in writing, software
      distributed under the License is distributed on an "AS IS" BASIS,
      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
      See the License for the specific language governing permissions and
      limitations under the License. See accompanying LICENSE file.
    -->
    <configuration>
        <!-- Site specific YARN configuration properties -->
        <property>
            <name>yarn.resourcemanager.ha.enabled</name>
            <value>true</value>
        </property>
        <property>
            <name>yarn.resourcemanager.connect.retry-interval.ms</name>
            <value>2000</value>
        </property>
        <property>
            <name>yarn.resourcemanager.ha.automatic-failover.enabled</name>
            <value>true</value>
        </property>
        <property>
            <name>yarn.resourcemanager.recovery.enabled</name>
            <value>true</value>
        </property>
        <property>
            <name>yarn.resourcemanager.cluster-id</name>
            <value>yarn</value>
        </property>
        <!--
        不同的节点只需要对这个参数做出相应的修改即可，也就是在热备的另一个节点上，该参数设置为rm2.即两个备份机器上的yarn-site.xml文件就是该参数不同。
        -->
        <property>
            <name>yarn.resourcemanager.ha.rm-ids</name>
            <value>resourcemanager1,resourcemanager2</value>
        </property>
        <!-- resourcemanager:white4,black13-->
        <property>
            <name>yarn.resourcemanager.ha.id</name>
            <value>resourcemanager1</value>
        </property>
        <property>
            <name>yarn.resourcemanager.hostname.resourcemanager1</name>
            <value>black13</value>
        </property>
        <property>
            <name>yarn.resourcemanager.hostname.resourcemanager2</name>
            <value>white4</value>
        </property>
        <property>
            <name>yarn.resourcemanager.address.resourcemanager1</name>
            <value>black13:8032</value>
        </property>
        <property>
            <name>yarn.resourcemanager.address.resourcemanager2</name>
            <value>white4:8032</value>
        </property>
        <property>
            <name>yarn.resourcemanager.webapp.address.resourcemanager1</name>
            <value>black13:8088</value>
        </property>
        <property>
            <name>yarn.resourcemanager.webapp.address.resourcemanager2</name>
            <value>white4:8088</value>
        </property>
        <property>
            <name>yarn.resourcemanager.zk-address</name>
            <value>black4:2181,black2:2181,black0:2181,black9:2181,black10:2181,black11:2181,white4:2181</value>
        </property>
        <property>
            <name>yarn.resourcemanager.recovery.enabled</name>
            <value>true</value>
        </property>
        <property>
            <name>yarn.nodemanager.aux-services.mapreduce.shuffle.class</name>
            <value>org.apache.hadoop.mapred.ShuffleHandler</value>
        </property>
    </configuration>
```
#### 5.4 配置mapred-site.xml文件

``` xml
    <?xml version="1.0"?>
    <?xml-stylesheet type="text/xsl" href="configuration.xsl"?>
    <!--
      Licensed under the Apache License, Version 2.0 (the "License");
      you may not use this file except in compliance with the License.
      You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

      Unless required by applicable law or agreed to in writing, software
      distributed under the License is distributed on an "AS IS" BASIS,
      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
      See the License for the specific language governing permissions and
      limitations under the License. See accompanying LICENSE file.
    -->
    <!-- Put site-specific property overrides in this file. -->
    <configuration>
        <property>
            <name>mapreduce.framework.name</name>
            <value>yarn</value>
        </property>
        <property>
            <name>mapred.job.tracker</name>
            <value>black13:9001</value>
        </property>
        <property>
            <name>mapred.system.dir</name>
            <value>file://${hadoop.tmp.dir}/../mapreduce/system</value>
        </property>
        <property>
            <name>mapred.local.dir</name>
            <value>file://${hadoop.tmp.dir}/../mapreduce/local</value>
        </property>
        <property>
            <name>mapreduce.jobtracker.http.address</name>
            <value>black13:50030</value>
        </property>
        <property>
            <name>mapreduce.jobhistory.address</name>
            <value>black13:10020</value>
        </property>
        <property>
            <name>mapreduce.jobhistory.webapp.address</name>
            <value>black13:19888</value>
        </property>
        <property>
            <name>mapreduce.cluster.temp.dir</name>
            <value>file:///${hadoop.tmp.dir}/../maperduce/tmp</value>
        </property>
        <property>
            <name>mapreduce.job.ubertask.enable</name>
            <value>true</value>
        </property>
    </configuration>
```
### 6. Hbase集群

HBase集群主要包括两部分服务，一种是主节点HMaster服务，另外就是HRegionServer服务。

``` xml
<?xml version="1.0"?>
<?xml-stylesheet type="text/xsl" href="configuration.xsl"?>
<!--
/**
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
-->
<configuration>
    <!-- 配置Hbase在Hadoop集群的根挂载点 -->
    <property>
		<name>hbase.rootdir</name>
		<value>hdfs://hadoop/hbase</value>
	</property>
	<property>
		<name>hbase.master</name>
		<value>60000</value>
	</property>
	<property>
		<name>hbase.cluster.distributed</name>
		<value>true</value>
	</property>
	<property>
		<name>hbase.zookeeper.property.clientPort</name>
		<value>2181</value>
	</property>
    <!-- 配置使用的Zookeeper节点信息 -->
	<property>
		<name>hbase.zookeeper.quorum</name>
		<value>black16,black17,black18,black19,black20</value>
	</property>
	<property>
		<name>zookeeper.session.timeout</name>
		<value>60000000</value>
	</property>
	<property>
		<name>dfs.support.append</name>
		<value>true</value>
	</property>
</configuration>
```
配置RegionServer节点集合

```
black17
black18
black19
black20
black16
```



### 7. Kafka集群

Kafka的配置提供三种方式：Single node – single broker集群；Single node – multiple broker集群；Multiple node – multiple broker集群。Hadoop依赖Zookeeper服务，参考4中配置Zookeeper。

在Kafka中，对于每个Node可以启动一个broker或者多个broker，每个broker对应一个server.properties文件。

```
# broker监听IP地址
host.name=black11
# broker日志存储目录
log.dirs=/home/gwd/data/kafka/kafka-logs-0
# Kafka连接Zookeeper服务器地址
zookeeper.connect=white1:2181,white2:2181,white3:2181
# Kafka中Zookeeper连接超时时间
zookeeper.connection.timeout.ms=6000
```

对于消费者与生产者可以自定义配置文件内容

```
# Kafka中的所有broker信息: host1:port1,host2:port2 ...
metadata.broker.list=black11:9092,white2:9092,white3:9092
# 定义切分方法
#partitioner.class=
# 定义生产者生产消息类型，是同步还是异步类型
producer.type=async
```



### 8. Redis集群

设置运行远程访问，

[参考资料1](https://stackoverflow.com/questions/37655523/redis-cluster-different-machines)

[参考资料2](https://serverfault.com/questions/815764/redis-cluster-3-master-nodes-minimum)

[参考资料3](http://www.aboutyun.com/thread-7155-1-1.html)

[参考资料4](http://codeflex.co/configuring-redis-cluster-on-linux/)

[参考资料5](https://seanmcgary.com/posts/how-to-build-a-fault-tolerant-redis-cluster-with-sentinel)

### 9.  启动

#### 9.1 初始化

　　（1）首先启动zookeeper集群

​        将所有的Zookeeper节点启动，执行zkServer.sh start。利用Jps命令，可以查看节点是否启动Zookeeper。

　　（2）对zookeeper集群进行格式化

　　　　执行：hdfs zkfc -formatZK

　　（3）启动JournalNode进程，注意这个在第一次的时候必须要按照这个顺序执行。否则后面hdfs格式化不了。

​                 利用命令：hadoop-daemon.sh  start journalnode 启动Journalnode

　　（4）格式化hadoop的集群，注意，第一次格式化必须首先启动上面的journalnode进程。并且，hadoop格式化的执行在某一个namenode节点上进行
　　　　　hdfs  namenode -format hadoop
　　（5）启动第（4）步格式化之后的namenode。就是说在第（4）步上面格式化后的namenode节点上启动namenode进程。
　　　　　hadoop-daemon.sh start namenode
　　（6）在另外一个namenode节点上按顺序执行如下两个命令来启动namenode进程。（本文中是hadoop5上执行）
　　　   　hdfs namenode -bootstrapStandby
　　　　   hadoop-daemon.sh start namenode
　　（7）在一个namenode节点上执行一下两个命令启动所有的进程：
　　　　　　start-dfs.sh
　　　　　　start-yarn.sh

#### 9.2 正常启动

​	启动集群的顺序依次是，先启动Zookeeper服务，然后启动Hadoop服务（可以使用已经丢弃脚本start-all.sh执行），最后是Hbase服务。kafka集群，先启动Zookeeper服务，然后是Kafka broker。

### 10. 检验

#### 10.1 检验Hadoop集群稳定性

通过kill active的namenode来验证namenode是否能自动切换。

#### 10.2 检验Hbase集群稳定性

通过Kill RegionServer服务，来验证Hbase集群是否能够进行进入读入写入操作。

### 11.  测试与查看

#### 11.1 Web服务查看



#### 11.2 shell测试

通过上传文件来检测HDFS的健康状态

　　执行 ：hadoop fs -put /hadoop /etc/hadoop/hdfs-site.xl  /
　　然后可以通过web查看hdfs-site.xml

#### 11.3 代码测试
