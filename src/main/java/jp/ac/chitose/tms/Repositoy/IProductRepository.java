package jp.ac.chitose.tms.Repositoy;

import java.util.List;

import jp.ac.chitose.tms.Bean.ProductItem;

public interface IProductRepository {
	public List<ProductItem> fetchProductItems();
	public int selectProductItems(String productName);
	public ProductItem fetchProductItem(int productId);
	public boolean insert(String name);
}
