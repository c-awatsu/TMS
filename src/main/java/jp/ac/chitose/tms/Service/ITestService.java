package jp.ac.chitose.tms.Service;

import java.util.List;

import jp.ac.chitose.tms.Bean.TestItem;

import org.apache.wicket.model.IModel;

public interface ITestService {
	public List<TestItem> fetchTestItems(int productId);
	public boolean upsert(IModel<TestItem> testItem);
}
