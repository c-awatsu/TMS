package jp.ac.chitose.tms.Bean;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class TestRecordItem implements Serializable{
	@NonNull
	private Integer testRecordId;

	@NonNull
	private Date testDate;

	@NonNull
	private Integer testerId;

	@NonNull
	private Boolean result;

	@NonNull
	private String note;

	@NonNull
	private Integer testId;

	public TestRecordItem(){
		this(0, new Date(0), 0, false, "", 0);
	}
}
