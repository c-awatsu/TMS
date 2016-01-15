package jp.ac.chitose.tms.Repositoy;

import java.util.List;

import jp.ac.chitose.tms.Bean.ProductItem;
import lombok.val;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository implements IProductRepository {

	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	@Autowired
	private JdbcTemplate dao;

	@Override
	public List<ProductItem> fetchProductItems() {
		val sql = "select * from product";
		val mapper = new BeanPropertyRowMapper<ProductItem>(ProductItem.class);
		return dao.query(sql, mapper);


	}

	@Override
	public int selectProductItems(String productName) {
		val sql = "select product_id from product "
				+ "where name = :1";
		val param = new MapSqlParameterSource()
					.addValue("1", productName);
		return jdbc.queryForObject(sql, param, Integer.class);
	}



}
