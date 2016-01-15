package jp.ac.chitose.tms.Service;

import java.util.List;

import jp.ac.chitose.tms.Bean.ProductItem;

public interface IProductService {
	public List<ProductItem> fetchProductItems();
}
