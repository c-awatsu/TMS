package jp.ac.chitose.tms.Service;

import java.util.List;
import java.util.stream.Collectors;

import jp.ac.chitose.tms.Bean.TestProgressItem;
import jp.ac.chitose.tms.Bean.TestRecordItem;
import jp.ac.chitose.tms.Repositoy.ITestRecordReposiory;
import jp.ac.chitose.tms.Repositoy.ITestRepository;
import lombok.val;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class TestRecordService implements ITestRecordService{

	@Autowired
	private ITestRecordReposiory trRepository;
	@Autowired
	private ITestRepository tRepository;

	@Override
	public TestProgressItem getTestProgress(long productId) {

		val testItems = tRepository.fetchTestItems(productId);
		int testItemsNum =  testItems.size();
		//TODO streamの返り値があっているか要チェック
		List<TestRecordItem> successTestItem =  testItems.stream()
				.map(i -> {
					return trRepository.selectLatestTestRecord(i.getTestId());
				}).filter(i->i.isResult())
				.collect(Collectors.toList());
		return new TestProgressItem(successTestItem.size(),testItemsNum);

	}
}
