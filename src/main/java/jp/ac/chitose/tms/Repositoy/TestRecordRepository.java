package jp.ac.chitose.tms.Repositoy;

import lombok.val;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
@Repository
public class TestRecordRepository implements ITestRecordReposiory {

	@Autowired
	private NamedParameterJdbcTemplate jdbc;

	@Override
	public boolean selectLatestResult(Integer testId) {
//		val sql = "SELECT result FROM TEST_RECORD where test_id = :1 and test_record_id = (select max(test_record_id) from test_record)";
		val sql = "select result from test_record where test_record_id = ("
				+ "select max(test_record_id) "
				+ "from test_record "
				+ "where test_id = :1 "
				+ ")";
		val param = new MapSqlParameterSource()
				.addValue("1", testId);
		val result = jdbc.queryForObject(sql, param,Boolean.class);
		if(result != null) return result;
		else return false;
	}

}
