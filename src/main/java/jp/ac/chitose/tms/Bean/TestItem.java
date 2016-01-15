package jp.ac.chitose.tms.Bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
@Data
@AllArgsConstructor
public class TestItem implements Serializable{
	@NonNull
	private Integer testId;
	@NonNull
	private String classification;
	@NonNull
	private String step;
	@NonNull
	private String expectedOutput;
	@NonNull
	private Integer productId;

	public TestItem(){
		this(0, "", "", "", 0);
	}
}
