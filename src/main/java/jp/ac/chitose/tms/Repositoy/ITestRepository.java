package jp.ac.chitose.tms.Repositoy;

import java.util.List;

import jp.ac.chitose.tms.Bean.TestItem;


public interface ITestRepository {
	public List<TestItem> fetchTestItems(long productId);

}
