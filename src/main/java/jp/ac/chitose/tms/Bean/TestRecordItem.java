package jp.ac.chitose.tms.Bean;

import java.io.Serializable;
import java.security.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class TestRecordItem implements Serializable{
	@NonNull
	private Integer testRecordId;

	private Timestamp date;

	@NonNull
	private Integer testerId;

	@NonNull
	private Boolean result;

	@NonNull
	private Integer testId;

	public TestRecordItem(){
		this(0, null, 0, false, 0);
	}
}
