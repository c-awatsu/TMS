package jp.ac.chitose.tms.Service;

import java.util.List;

import org.apache.wicket.model.IModel;

import jp.ac.chitose.tms.Bean.TestItem;

public interface ITestService {

	public List<TestItem> fetchTestItems(int productId);
	public boolean upsert(IModel<TestItem> testItem);
	public TestItem fetchTestItem(int testId);
}
