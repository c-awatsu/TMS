package jp.ac.chitose.tms.Service;

import java.util.Optional;

import jp.ac.chitose.tms.Bean.Sign;
import jp.ac.chitose.tms.Bean.SignIn;
import jp.ac.chitose.tms.Bean.SignUp;

public interface ISignService {
	public boolean createAccount(SignUp signUp);
	public Optional<Sign> authenticate(SignIn sign);
	public boolean exsitsUser();
}
