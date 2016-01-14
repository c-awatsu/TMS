package jp.ac.chitose.tms.Repositoy;

import java.util.List;

import jp.ac.chitose.tms.Bean.TestItem;
import lombok.val;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TestRepository implements ITestRepository {
	@Autowired
	private NamedParameterJdbcTemplate jdbc;

	@Override
	public List<TestItem> fetchTestItems(long productId) {
		val sql = "select * from test "
				+ "where product_id = :1 ";
		val param = new MapSqlParameterSource()
				.addValue("1", productId);
		return jdbc.queryForList(sql, param,TestItem.class);
	}



}
