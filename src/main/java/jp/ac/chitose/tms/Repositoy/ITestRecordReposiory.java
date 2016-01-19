package jp.ac.chitose.tms.Repositoy;

import java.util.List;

import jp.ac.chitose.tms.Bean.TestRecordItem;


public interface ITestRecordReposiory {
	public TestRecordItem selectLatestResult(int testId);
	public boolean isEmpty(int testId);
	public List<TestRecordItem> fetchTestRecordItems(int testId);
}
