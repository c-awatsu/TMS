package jp.ac.chitose.tms.Repositoy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.ac.chitose.tms.Bean.Sign;
import jp.ac.chitose.tms.Bean.SignIn;
import lombok.val;

@Repository
public class SignRepository implements ISignRepository{

	@Autowired
	private NamedParameterJdbcTemplate jdbc;

	@Override
	public int insert(String loginId, String passphrase, String nickName) {
		val sql = "insert into account "
				+ "(login_id,passphrase,nickname) "
				+ "values(:1,:2,:3) ";

		val param = new MapSqlParameterSource()
				.addValue("1", loginId)
				.addValue("2", passphrase)
				.addValue("3", nickName);
		return jdbc.update(sql, param);

	}

	@Override
	public SignIn fetchAccountId(String loginId, String passphrase) {
		val sql = "select account_id,nickname from account where "
				+ "login_id = :1 "
				+ "and passphrase = :2 ";
		val param = new MapSqlParameterSource()
				.addValue("1", loginId)
				.addValue("2", passphrase);
		val mapper = new  BeanPropertyRowMapper<SignIn>(SignIn.class);
		return jdbc.queryForObject(sql, param,mapper);
	}

	@Override
	public List<Sign> fetchAccountId() {
		val sql = "select account_id from account";
		val mapper =new BeanPropertyRowMapper<Sign>(Sign.class);
		return jdbc.query(sql, mapper);
	}

	@Override
	public String fetchNickName(int accountid) {
		val sql = "select nickname from account where account_id = :1";
		val param  = new MapSqlParameterSource()
						.addValue("1", accountid);
		return jdbc.queryForObject(sql, param, String.class);
	}

}
