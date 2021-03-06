package jp.ac.chitose.tms.Service;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import jp.ac.chitose.tms.Bean.Sign;
import jp.ac.chitose.tms.Bean.SignIn;
import jp.ac.chitose.tms.Bean.SignUp;
import jp.ac.chitose.tms.Repositoy.ISignRepository;
@Service
public class SignService implements ISignService {

	@Autowired
	private ISignRepository signRepository;

	@Override
	public boolean createAccount(SignUp signUp) {
		boolean flag = false;
		try{
			flag = signRepository.insert(signUp.getLoginId(),
										 DigestUtils.sha256Hex(signUp.getPassphrase()),
										 signUp.getNickName()
										 ) == 1;
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public Sign authenticate(SignIn signIn) {
		Sign sign = null;
		try{
			SignIn accountData = signRepository.fetchAccountId(signIn.getLoginId(),DigestUtils.sha256Hex(signIn.getPassphrase()));
			sign = new Sign(accountData.getAccountId(),signIn.getLoginId(),accountData.getNickName());
		}catch(EmptyResultDataAccessException e){
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		return sign;
	}

	@Override
	public boolean exsitsUser() {
		boolean flag = false;
		try{
			flag = signRepository.fetchAccountId().stream().findFirst().isPresent();
		}catch(DataAccessException e){
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public String fetchNickName(int accountId) {
		return signRepository.fetchNickName(accountId);
	}



}
