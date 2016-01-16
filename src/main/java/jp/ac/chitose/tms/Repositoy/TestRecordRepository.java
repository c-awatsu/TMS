package jp.ac.chitose.tms.Repositoy;

import jp.ac.chitose.tms.Bean.TestRecordItem;
import lombok.val;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
@Repository
public class TestRecordRepository implements ITestRecordReposiory {

	@Autowired
	private NamedParameterJdbcTemplate jdbc;

	@Override
	public TestRecordItem selectLatestResult(int testId) {
		val sql = "select * from test_record where test_record_id = ("
				+ "select max(test_record_id) "
				+ "from test_record "
				+ "where test_id = :1 "
				+ ")";
		val param = new MapSqlParameterSource()
				.addValue("1", testId);
		val mapper = new BeanPropertyRowMapper<TestRecordItem>(TestRecordItem.class);
		val result = jdbc.queryForObject(sql, param, mapper);
		return result;
	}

	@Override
	public boolean isEmpty(int testId) {
		val sql = "select count(*) from test_record where test_id = :1";
		val param = new MapSqlParameterSource()
						.addValue("1", testId);
		val num = jdbc.queryForObject(sql, param,Integer.class);
		return num == 0;
	}

}
