package jp.ac.chitose.tms.Service;

import java.util.List;

import org.apache.wicket.model.IModel;

import jp.ac.chitose.tms.Bean.ProductItem;

public interface IProductService {
	public List<ProductItem> fetchProductItems();
	public ProductItem getProductItem(int productId);
	public boolean insertProductItem(IModel<ProductItem> productNameModel);
	public boolean upsert(IModel<ProductItem> productItemsModel);
}
