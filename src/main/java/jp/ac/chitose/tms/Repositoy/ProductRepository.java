package jp.ac.chitose.tms.Repositoy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.ac.chitose.tms.Bean.ProductItem;
import lombok.val;

@Repository
public class ProductRepository implements IProductRepository {

	@Autowired
	private NamedParameterJdbcTemplate jdbc;

	@Override
	public List<ProductItem> fetchProductItems() {
		val sql = "select * from product";
		val mapper = new BeanPropertyRowMapper<ProductItem>(ProductItem.class);
		return jdbc.query(sql, mapper);
	}

	@Override
	public ProductItem fetchProductItem(int productId) {
		val sql ="select * from product where product_id = :1";
		val param = new MapSqlParameterSource()
						.addValue("1", productId);
		val mapper = new BeanPropertyRowMapper<ProductItem>(ProductItem.class);
		return jdbc.queryForObject(sql, param, mapper);
	}

	@Override
	public boolean insert(String name) {
		val sql = "insert into product (name) values (:1)" ;
		val param = new MapSqlParameterSource()
						.addValue("1", name);
		return jdbc.update(sql, param) == 1;
	}

	@Override
	public boolean update(String name, int productId) {
		val sql = "update product set name = :1 "
				+ "where product_id = :2";
		val param = new MapSqlParameterSource()
						.addValue("1", name)
						.addValue("2", productId);
		return jdbc.update(sql, param) == 1;
	}

}
