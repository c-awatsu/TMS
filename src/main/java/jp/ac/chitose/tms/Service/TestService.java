package jp.ac.chitose.tms.Service;

import java.util.List;

import jp.ac.chitose.tms.Bean.TestItem;
import jp.ac.chitose.tms.Repositoy.ITestRepository;

import org.apache.wicket.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class TestService implements ITestService{

	@Autowired
	private ITestRepository testRepository;

	@Override
	public List<TestItem> fetchTestItems(int productId) {
		return testRepository.fetchTestItems(productId);
	}

	@Override
	public boolean inputTestItem(TestItem item) {
		return testRepository.insert(item);
	}

	@Override
	public boolean upsert(IModel<TestItem> testItem) {
		if(testItem.getObject().getTestId() == 0){
			return testRepository.insert(testItem.getObject());
		}else{
			return testRepository.update(testItem.getObject());
		}
	}

}
