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
	private Integer accountId;

	@NonNull
	private String loginId;


	/**
	 * Constructor.
	 */
	public Sign() {
		this(0, "");
	}

}
