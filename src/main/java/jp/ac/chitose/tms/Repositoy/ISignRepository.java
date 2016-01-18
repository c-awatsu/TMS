package jp.ac.chitose.tms.Repositoy;

import java.util.List;

import jp.ac.chitose.tms.Bean.Sign;

public interface ISignRepository {
	public int insert(String loginId,String passphrase,String nickName);
	public int fetchAccountId(String loginId,String passphrase);
	public List<Sign> fetchAccountId();
}
