package jp.ac.chitose.tms.Service;

import java.util.List;
import java.util.stream.Collectors;

import jp.ac.chitose.tms.Bean.ProductItem;
import jp.ac.chitose.tms.Repositoy.IProductRepository;
import jp.ac.chitose.tms.Repositoy.ITestRepository;
import lombok.val;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {

	@Autowired
	private IProductRepository productRepository;
	@Autowired
	private ITestRepository testRepository;

	@Override
	public List<ProductItem> fetchProductItems() {
		return productRepository.fetchProductItems();
	}

	@Override
	public int countProgresOfTest(String productName) {
		val testItems =  testRepository.fetchTestItems(
				productRepository.selectProductItems(productName));
		val testRecordItems = testItems.stream().map(t ->
				testRepository.fetchTestRecordItems(t.getTestId())
			).collect(Collectors.toList());
		//TODO テスト進行度を計算して返す
		int size = testItems.size();
		int testFinished = 0;

		return testFinished;
	}

}
