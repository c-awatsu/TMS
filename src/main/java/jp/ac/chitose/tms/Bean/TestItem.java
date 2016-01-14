package jp.ac.chitose.tms.Bean;

import java.io.Serializable;

import lombok.Data;
@Data
public class TestItem implements Serializable{
	private long testId;
	private String classification;
	private String step;
	private String exepectedOutput;
	private long productId;

	public TestItem(){
		testId = 0;
		classification = "";
		step = "";
		exepectedOutput = "";
		productId = 0;
	}
}
