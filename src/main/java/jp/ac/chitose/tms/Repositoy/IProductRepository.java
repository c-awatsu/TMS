package jp.ac.chitose.tms.Repositoy;

import java.util.List;

import jp.ac.chitose.tms.Bean.ProductItem;

public interface IProductRepository {
	public List<ProductItem> fetchProductItems();
	public ProductItem fetchProductItem(int productId);
	public boolean insert(String name);
	public boolean update(String name,int productId);
}
