package jp.ac.chitose.tms.Service;

import jp.ac.chitose.tms.Bean.TestProgressItem;
import jp.ac.chitose.tms.Bean.TestRecordItem;

public interface ITestRecordService {
	public TestProgressItem getTestProgress(Integer productId);
	public TestRecordItem getLatestTestRecord(int testId);
}
