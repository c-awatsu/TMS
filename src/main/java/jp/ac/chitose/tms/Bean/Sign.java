package jp.ac.chitose.tms.Bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Value;

@Value
@AllArgsConstructor

public class Sign implements Serializable {
	private static final long serialVersionUID = 4069895122685977917L;

	@NonNull
	private Integer AccountId;

	@NonNull
	private String loginId;

	@NonNull
	private String nickname;


	/**
	 * Constructor.
	 */
	public Sign() {
		this(0,"","");
	}

}
