package jp.ac.chitose.tms.Repositoy;

import java.util.List;

import jp.ac.chitose.tms.Bean.Sign;
import jp.ac.chitose.tms.Bean.SignIn;

public interface ISignRepository {
	public int insert(String loginId,String passphrase,String nickName);
	public SignIn fetchAccountId(String loginId,String passphrase);
	public List<Sign> fetchAccountId();
}
