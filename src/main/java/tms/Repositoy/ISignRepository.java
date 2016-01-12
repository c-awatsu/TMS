package tms.Repositoy;

import java.util.List;

public interface ISignRepository {
	public int insert(String loginId,String passphrase,String nickName);
	public int fetchAccountId(String loginId,String passphrase);
	public List<Integer> fetchAccountId();
}
