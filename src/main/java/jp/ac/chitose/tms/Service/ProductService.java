package jp.ac.chitose.tms.Service;

import java.util.List;

import jp.ac.chitose.tms.Bean.ProductItem;
import jp.ac.chitose.tms.Repositoy.IProductRepository;
import jp.ac.chitose.tms.Repositoy.ITestRepository;

import org.apache.wicket.model.IModel;
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
	public ProductItem getProductItem(int productId) {
		return productRepository.fetchProductItem(productId);
	}

	@Override
	public boolean insertProductItem(IModel<ProductItem> productNameModel) {
		return productRepository.insert(productNameModel.getObject().getName());
	}
}
