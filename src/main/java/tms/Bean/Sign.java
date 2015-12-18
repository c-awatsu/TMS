package tms.Bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Value;

import org.apache.wicket.authroles.authorization.strategies.role.Roles;

/**
 * ユーザ認証済みデータ
 *
 * @author Hiroto yamakawa
 */
@Value
@AllArgsConstructor

public class Sign implements Serializable {
	private static final long serialVersionUID = 4069895122685977885L;

	@NonNull
	private Integer accountId;

	@NonNull
	private String loginId;

	@NonNull
	private Roles role;

	/**
	 * Constructor.
	 */
	public Sign() {
		this(0, "", new Roles());
	}

}
