package jp.ac.chitose.tms.Service;

import java.util.List;

import jp.ac.chitose.tms.Bean.ProductItem;

import org.apache.wicket.model.IModel;

public interface IProductService {
	public List<ProductItem> fetchProductItems();
	public ProductItem getProductItem(int productId);
	public boolean insertProductItem(IModel<ProductItem> productNameModel);

}
