package jp.ac.chitose.tms.Bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class ProductItem implements Serializable{
	private long productId;
	private String productName;

	public ProductItem(){
		productId = 0;
		productName = "";
	}
}
