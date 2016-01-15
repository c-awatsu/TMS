package jp.ac.chitose.tms.Repositoy;

import java.util.ArrayList;
import java.util.List;

import jp.ac.chitose.tms.Bean.ProductItem;
import lombok.val;

import org.springframework.beans.factory.annotation.Autowired;
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
//		val sql = "select * from product";
//		val mapper = new BeanPropertyRowMapper<ProductItem>(ProductItem.class);
//		return dao.query(sql, mapper);
		//TODO 結果の一部カラムが[unread]になる不具合に対しての応急処置
		return tFetchProductItems();

	}

	private List<ProductItem> tFetchProductItems() {
		//これなら正常な結果を得られるため、mapper又はdao.queryが悪さしてると思われる
		val result = new ArrayList<ProductItem>();
		val idSql = "select product_id from product";
		val nameSql = "select name from product";
		val ids = dao.queryForList(idSql, Integer.class);
		val names = dao.queryForList(nameSql, String.class);
		val resultLength = ids.size();
		for(int i = 0; i < resultLength; i++){
			result.add(new ProductItem(ids.get(i), names.get(i)));
		}
		return result;
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
