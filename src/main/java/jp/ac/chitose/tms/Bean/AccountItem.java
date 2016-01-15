package jp.ac.chitose.tms.Bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class AccountItem implements Serializable{
	@NonNull
	private Integer accountId;

	@NonNull
	private String loginId;

	@NonNull
	private String passphrase;

	public AccountItem(){
		this(0,"","");
	}
}
