package tms.Bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class AccountItem implements Serializable{
	private long accountId;
	private String loginId;
	private String passphrase;

	public AccountItem(){
		accountId = 0;
		loginId = "";
		passphrase = "";
	}
}
