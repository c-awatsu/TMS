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
	public TestRecordItem selectLatestTestRecord(Integer testId) {
		val sql = "SELECT * FROM TEST_RECORD where test_id = :1 and test_record_id = (select max(test_record_id) from test_record)";
		val param = new MapSqlParameterSource()
				.addValue("1", testId);
		val mapper = new BeanPropertyRowMapper<TestRecordItem>(TestRecordItem.class);
		val result = jdbc.query(sql, param, mapper);
		if(result.size() > 0) return result.get(0);
		else return new TestRecordItem();
		//TODO ProductRepositoryと同様の不具合
		//結果の一部のカラムが全て[unread]になる
	}

}
