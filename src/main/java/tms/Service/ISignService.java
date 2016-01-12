package tms.Service;

import java.util.Optional;

import tms.Bean.Sign;
import tms.Bean.SignIn;
import tms.Bean.SignUp;

public interface ISignService {
	public boolean createAccount(SignUp signUp);
	public Optional<Sign> authenticate(SignIn sign);
	public boolean exsitsUser();
}
