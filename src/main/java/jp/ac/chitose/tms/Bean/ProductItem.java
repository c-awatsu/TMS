package jp.ac.chitose.tms.Bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class ProductItem implements Serializable{
	@NonNull
	private Integer productId;
	@NonNull
	private String productName;

	public ProductItem(){
		this(0,"");
	}

}
