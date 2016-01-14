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
	public List<TestItem> fetchTestItems(IModel<Integer> productIdModel) {
		return testRepository.fetchTestItems(productIdModel.getObject());
	}
}
