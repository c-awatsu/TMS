package jp.ac.chitose.tms.Service;

import java.util.List;

import jp.ac.chitose.tms.Bean.TestItem;

public interface ITestService {
	public List<TestItem> fetchTestItems(int productId);
	public boolean inputTestItem(TestItem item);
}
