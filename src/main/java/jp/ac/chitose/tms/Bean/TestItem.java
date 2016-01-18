package jp.ac.chitose.tms.Bean;

import java.io.Serializable;

import lombok.Data;
import lombok.NonNull;
@Data
public class TestItem implements Serializable{
	private static final long serialVersionUID = 42L;

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

	}
}
