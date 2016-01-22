package jp.ac.chitose.tms.Service;

import java.util.List;

import org.apache.wicket.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.ac.chitose.tms.Bean.ProductItem;
import jp.ac.chitose.tms.Repositoy.IProductRepository;

@Service
public class ProductService implements IProductService {

	@Autowired
	private IProductRepository productRepository;

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

	@Override
	public boolean upsert(IModel<ProductItem> productItemsModel) {
		if(productItemsModel.getObject().getProductId() == 0){
			return productRepository.insert(productItemsModel.getObject().getName());
		}else{
			return productRepository.update(productItemsModel.getObject().getName(), productItemsModel.getObject().getProductId());
		}
	}
}


