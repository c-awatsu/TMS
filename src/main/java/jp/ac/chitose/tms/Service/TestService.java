package jp.ac.chitose.tms.Service;

import java.util.List;

import jp.ac.chitose.tms.Bean.TestItem;
import jp.ac.chitose.tms.Repositoy.ITestRepository;

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
}
