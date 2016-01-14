package jp.ac.chitose.tms.Repositoy;

import jp.ac.chitose.tms.Bean.TestRecordItem;
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
	public TestRecordItem selectLatestTestRecord(long testId) {
		val sql = "SELECT * FROM TEST_RECORD where test_id = :1 and test_record_id = (select max(test_record_id) from test_record)";
		val param = new MapSqlParameterSource()
				.addValue("1", testId);
		return jdbc.queryForObject(sql, param, TestRecordItem.class);
	}

}
