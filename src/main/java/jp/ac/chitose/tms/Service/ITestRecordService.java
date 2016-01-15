package jp.ac.chitose.tms.Service;

import jp.ac.chitose.tms.Bean.TestProgressItem;

public interface ITestRecordService {
	public TestProgressItem getTestProgress(Integer productId);
}
