package jp.ac.chitose.tms.Repositoy;

import jp.ac.chitose.tms.Bean.TestRecordItem;

public interface ITestRecordReposiory {
	public TestRecordItem selectLatestTestRecord(long testId);

}
