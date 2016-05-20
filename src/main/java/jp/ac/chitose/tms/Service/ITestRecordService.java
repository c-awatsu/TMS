package jp.ac.chitose.tms.Service;

import java.util.List;

import jp.ac.chitose.tms.Bean.TestProgressItem;
import jp.ac.chitose.tms.Bean.TestRecordItem;

public interface ITestRecordService {
	public TestProgressItem getTestProgress(Integer productId);
	public TestRecordItem getLatestTestRecord(int testId);
	public List<TestRecordItem> fetchTestRecordItems(int testId);
	public boolean insert(TestRecordItem testRecordItem);
	public boolean delete(int testId);
}
