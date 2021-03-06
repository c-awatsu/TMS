package jp.ac.chitose.tms.Service;

import java.util.List;

import jp.ac.chitose.tms.Bean.TestItem;
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
//
//	@Override
//	public TestProgressItem getTestProgress(Integer productId) {
//
//		val testItems = tRepository.fetchTestItems(productId);
//		int testItemsNum =  testItems.size();
//		//TODO streamの返り値があっているか要チェック
//		List<TestRecordItem> successTestItem =  testItems.stream()
//				.map(i -> {
//					return trRepository.selectLatestTestRecord(i.getTestId());
//				}).filter(i->i.getResult())
//				.collect(Collectors.toList());
//		return new TestProgressItem(successTestItem.size(),testItemsNum);
//	}

	@Override
	public TestProgressItem getTestProgress(Integer productId) {
		//TODO streamやだー！
		int count = 0;
		val testItems = tRepository.fetchTestItems(productId);
		for(TestItem testItem: testItems){
			if(trRepository.isEmpty(testItem.getTestId()))continue;
			if(trRepository.selectLatestResult(testItem.getTestId()).getResult())count++;
		}
		return new TestProgressItem(count, testItems.size());
	}

	@Override
	public TestRecordItem getLatestTestRecord(int testId) {
		if(trRepository.isEmpty(testId))return null;
		else return trRepository.selectLatestResult(testId);
	}

	@Override
	public List<TestRecordItem> fetchTestRecordItems(int testId){
		return trRepository.fetchTestRecordItems(testId);
	}

	@Override
	public boolean insert(TestRecordItem testRecordItem) {
		return trRepository.insert(testRecordItem) != 0;//更新件数が0件ならfalse
	}

	@Override
	public boolean delete(int testId) {
		return trRepository.delete(testId) == 1;//1行だけ更新できたら成功してる=true
	}

}
