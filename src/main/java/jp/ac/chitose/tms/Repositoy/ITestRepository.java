package jp.ac.chitose.tms.Repositoy;

import java.util.List;

import jp.ac.chitose.tms.Bean.TestItem;
import jp.ac.chitose.tms.Bean.TestRecordItem;


public interface ITestRepository {
	public List<TestItem> fetchTestItems(int productId);
	public List<TestRecordItem> fetchTestRecordItems(long testId);
}
