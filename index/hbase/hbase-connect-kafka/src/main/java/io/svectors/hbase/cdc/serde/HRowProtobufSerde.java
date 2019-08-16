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
package io.svectors.hbase.cdc.serde;

import com.google.protobuf.InvalidProtocolBufferException;
import io.svectors.hbase.cdc.ReplicationException;
import io.svectors.hbase.cdc.model.HRow;
import io.svectors.hbase.cdc.protobuf.generated.HColumnProtos;
import io.svectors.hbase.cdc.protobuf.generated.HRowProtos;
import org.apache.hadoop.hbase.util.ByteStringer;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ravi.magham
 */
public class HRowProtobufSerde implements Serializer<HRow>, Deserializer<HRow> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // NO-OP
    }

    @Override
    public HRow deserialize(String topic, byte[] data) {
        try {
            HRowProtos.Row rowProto = HRowProtos.Row.parseFrom(data);
            List<HColumnProtos.HColumn> columnsProto = rowProto.getColumnList();

            List<HRow.HColumn> columns = columnsProto.stream().map(col -> {
                HRow.HColumn column = new HRow.HColumn(col.getFamily().toByteArray(),
                    col.getQualifier().toByteArray(),
                    col.getValue().toByteArray(),
                    col.getTimestamp());
                return column;

            }).collect(Collectors.toList());
            final byte[] rowkey = rowProto.getRow().toByteArray();
	          final HRow.RowOp rowOp = HRow.RowOp.valueOf(rowProto.getOp().name());
            final HRow row = new HRow(rowkey, rowOp, columns);
            return row;
        } catch (InvalidProtocolBufferException e) {
            throw new ReplicationException(e);
        }
    }

    /**
     * Serializes each {@link HRow} to byte[] through protobuf.
     * @param topic
     * @param row
     * @return
     */
    @Override
    public byte[] serialize(String topic, HRow row) {
        final HRowProtos.Row.Builder rowBuilder = HRowProtos.Row.newBuilder();
        row.getColumns().stream().forEach(column -> {
	        HColumnProtos.HColumn.Builder cellBuilder = HColumnProtos.HColumn.newBuilder();
	        cellBuilder.setFamily(ByteStringer.wrap(column.getFamily()));
	        cellBuilder.setQualifier(ByteStringer.wrap(column.getQualifier()));
	        cellBuilder.setValue(ByteStringer.wrap(column.getValue()));
	        cellBuilder.setTimestamp(column.getTimestamp());
	        rowBuilder.addColumn(cellBuilder.build());
        });
        rowBuilder.setOp(HRowProtos.RowOp.valueOf(row.getRowOp().name()));
	      rowBuilder.setRow(ByteStringer.wrap(row.getRowKey()));
        return rowBuilder.build().toByteArray();
    }


    @Override
    public void close() {
        // NO-OP
    }
}
