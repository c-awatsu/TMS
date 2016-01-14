package jp.ac.chitose.tms.Bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

//lombokから引数ありコンストラクタを自動生成してくれるアノテーション
//初期化コンストラクタを生成しているわけではない
@AllArgsConstructor
@Data
public class SignUp implements Serializable {
	@NonNull
	private String loginId;

	@NonNull
	private String passphrase;

	@NonNull
	private String nickName;

	/**
	 * Constructor.
	 */
	public SignUp(){
		this("","","");
	}
}
