package jp.ac.chitose.tms.Repositoy;

import java.util.List;

import jp.ac.chitose.tms.Bean.TestItem;
import lombok.val;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TestRepository implements ITestRepository {
	@Autowired
	private NamedParameterJdbcTemplate jdbc;

	@Override
	public List<TestItem> fetchTestItems(int productId) {
		val sql = "select * from test "
				+ "where product_id = :1 ";
		val param = new MapSqlParameterSource().addValue("1", productId);
		val mapper = new BeanPropertyRowMapper<TestItem>(TestItem.class);
		return jdbc.query(sql, param, mapper);
	}

	@Override
	public boolean insert(TestItem item) {
		val sql = "insert into test "
				+ "(classification, step, expected_output, product_id) "
				+ "values (:1, :2, :3, :4)";
		val param = new MapSqlParameterSource()
						.addValue("1", item.getClassification())
						.addValue("2", item.getStep())
						.addValue("3", item.getExpectedOutput())
						.addValue("4", item.getProductId());
		return jdbc.update(sql, param) == 1;
	}

	@Override
	public boolean update(TestItem item) {
		System.out.println(item.getTestId());
		val sql = "update test set classification = :1,step = :2,expected_output = :3 "
				+ "where product_id = :4 and test_id = :5";
		val param = new MapSqlParameterSource()
			.addValue("1", item.getClassification())
			.addValue("2", item.getStep())
			.addValue("3", item.getExpectedOutput())
			.addValue("4", item.getProductId())
			.addValue("5",item.getTestId());
		return jdbc.update(sql,param) == 1;
	}

}
