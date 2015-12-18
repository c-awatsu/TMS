package tms.Bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class ProductItem implements Serializable{
	private long productId;
	private String name;

	public ProductItem(){
		productId = 0;
		name = "";
	}
}
