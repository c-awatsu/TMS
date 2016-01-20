package jp.ac.chitose.tms.Service;

import jp.ac.chitose.tms.Bean.Sign;
import jp.ac.chitose.tms.Bean.SignIn;
import jp.ac.chitose.tms.Bean.SignUp;

public interface ISignService {
	public boolean createAccount(SignUp signUp);
	public Sign authenticate(SignIn sign);
	public boolean exsitsUser();
	public String fetchNickName(int accountId);
}
