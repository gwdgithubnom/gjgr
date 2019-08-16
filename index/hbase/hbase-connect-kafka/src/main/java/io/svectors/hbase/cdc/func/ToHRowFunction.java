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
package io.svectors.hbase.cdc.func;

import com.google.common.base.Preconditions;
import io.svectors.hbase.cdc.model.HRow;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.protobuf.generated.CellProtos;

import java.util.List;
import java.util.function.BiFunction;

import static java.util.stream.Collectors.toList;

/**
 * @author ravi.magham
 */
public class ToHRowFunction implements BiFunction<byte[], List<Cell>, HRow> {


    @Override
    public HRow apply(byte[] rowkey, List<Cell> cells) {

        Preconditions.checkNotNull(rowkey);
        Preconditions.checkNotNull(cells);
        final List<HRow.HColumn> columns = toRowColumns(cells);
        HRow.RowOp rowOp = null;
        final Cell cell = cells.get(0);
        final CellProtos.CellType type = CellProtos.CellType.valueOf(cell.getTypeByte());
        switch (type) {
            case DELETE:
            case DELETE_COLUMN:
            case DELETE_FAMILY:
                rowOp = HRow.RowOp.DELETE;
                break;
            case PUT:
                rowOp = HRow.RowOp.PUT;
                break;
        }
        final HRow row = new HRow(rowkey, rowOp, columns);
        return row;
    }

    /**
     * maps each {@linkplain Cell} to a {@linkplain HRow.HColumn}
     * @param cells
     * @return
     */
    private List<HRow.HColumn> toRowColumns(final List<Cell> cells) {
        final List<HRow.HColumn> columns = cells.stream().map(cell -> {
            byte[] family = CellUtil.cloneFamily(cell);
            byte[] qualifier = CellUtil.cloneQualifier(cell);
            byte[] value = CellUtil.cloneValue(cell);
            long timestamp = cell.getTimestamp();
            final HRow.HColumn column = new HRow.HColumn(family, qualifier, value, timestamp);
            return column;
        }).collect(toList());

        return columns;
    }
}
