package jp.ac.chitose.tms.Bean;

import java.io.Serializable;
import java.security.Timestamp;

import lombok.Data;

@Data
public class TestRecordItem implements Serializable{
	private long testRecordId;
	private Timestamp date;
	private long testerId;
	private boolean result;
	private int testId;

	public TestRecordItem(){
		testRecordId = 0;
		date = null;
		testerId = 0;
		result = false;
		testId = 0;
	}
}
