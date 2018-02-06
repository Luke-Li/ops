package com.peseer.ops.model;

import com.peseer.ops.model.base.BaseDailyOperationRecord;

/**
 *
 */
@SuppressWarnings("serial")
public class DailyOperationRecord extends BaseDailyOperationRecord<DailyOperationRecord> {
	public static final DailyOperationRecord dao = new DailyOperationRecord();

	public void saveRecord(int operationId, String operationTable,String operator, String operationType){
		DailyOperationRecord record = new DailyOperationRecord();
		record.setOperationId(operationId);
		record.setOperationTable(operationTable);
		record.setOperater(operator);
		record.setOperationType(operationType);
		record.save();
	}
}
