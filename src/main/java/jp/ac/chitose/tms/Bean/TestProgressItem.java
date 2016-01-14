package jp.ac.chitose.tms.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TestProgressItem {
	private int progress;
	private int testItemsNum;

	@Override
	public String toString(){
		return progress+"/"+testItemsNum;
	}

	public TestProgressItem(){
		this(0,0);
	}
}
