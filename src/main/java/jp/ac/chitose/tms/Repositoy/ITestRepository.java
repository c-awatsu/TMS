package jp.ac.chitose.tms.Repositoy;

import java.util.List;

import jp.ac.chitose.tms.Bean.TestItem;


public interface ITestRepository {
	public List<TestItem> fetchTestItems(int productId);
	public boolean insert(TestItem item);
	public boolean update(TestItem item);
	public TestItem fetchTestItem(int testId);
}
