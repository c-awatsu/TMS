package jp.ac.chitose.tms.Bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class SignIn implements Serializable{
	private static final long serialVersionUID = -4301784030025895482L;

	@NonNull
	private String loginId;

	@NonNull
	private String passphrase;

	@NonNull
	private String nickName;

	/**
	 * Constructor.
	 */
	public SignIn(){
		this("","","");
	}
}
