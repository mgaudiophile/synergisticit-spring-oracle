package com.synergisticit.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.synergisticit.domain.Product;

import oracle.jdbc.OracleTypes;

@Repository
public class JdbcProductRepositoryImpl implements ProductRepository {

	private JdbcTemplate jdbc;
	
	@Autowired
	public JdbcProductRepositoryImpl(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findAll() {
		System.out.println("JdbcProductRepositoryImpl.findAll()................");
		
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbc);
		
		jdbcCall.withSchemaName("SCOTT")
			    .withCatalogName("SHOPPING")
			    .withProcedureName("SELECT_PRODUCT")
			    .returningResultSet("RC", this::mapRowToProduct);
			
		Map<String, Object> result = jdbcCall.execute(new HashMap<String, Object>());
		
		return (List<Product>) result.get("RC");
	}

	@Override
	public Product save(Product product) {
		System.out.println("JdbcProductRepositoryImpl.save()................");
		
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbc);
		
		jdbcCall.withSchemaName("SCOTT")
			    .withCatalogName("SHOPPING")
			    .withProcedureName("INSERT_PRODUCT");
			
		jdbcCall.addDeclaredParameter(new SqlParameter("CAT_ID", OracleTypes.NUMBER));
		jdbcCall.addDeclaredParameter(new SqlParameter("PROD_NAME", OracleTypes.NUMBER));
		jdbcCall.addDeclaredParameter(new SqlParameter("PRICE", OracleTypes.NUMBER));
		jdbcCall.addDeclaredParameter(new SqlParameter("STOCK", OracleTypes.NUMBER));
		jdbcCall.addDeclaredParameter(new SqlParameter("VENDOR", OracleTypes.VARCHAR));
		
		jdbcCall.execute(product.getCat_id(), product.getProd_name(), product.getPrice(), product.getStock(), product.getVendor());
		
		return product;
	}

	@Override
	public Product update(Product product) {
		System.out.println("JdbcProductRepositoryImpl.updateById()................");
		
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbc);
		
		jdbcCall.withSchemaName("SCOTT")
			    .withCatalogName("SHOPPING")
			    .withProcedureName("UPDATE_PRODUCT");
			
		jdbcCall.addDeclaredParameter(new SqlParameter("PROD_ID", OracleTypes.NUMBER));
		jdbcCall.addDeclaredParameter(new SqlParameter("CAT_ID", OracleTypes.NUMBER));
		jdbcCall.addDeclaredParameter(new SqlParameter("PROD_NAME", OracleTypes.NUMBER));
		jdbcCall.addDeclaredParameter(new SqlParameter("PRICE", OracleTypes.NUMBER));
		jdbcCall.addDeclaredParameter(new SqlParameter("STOCK", OracleTypes.NUMBER));
		jdbcCall.addDeclaredParameter(new SqlParameter("VENDOR", OracleTypes.VARCHAR));
		
		jdbcCall.execute(product.getProd_id(), product.getCat_id(), product.getProd_name(), product.getPrice(), product.getStock(), product.getVendor());
		
		return product;
	}
	
	@Override
	public void delete(int prod_id, String prod_name) {
		System.out.println("JdbcProductRepositoryImpl.delete()................" + prod_id + " " + prod_name);
		
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbc);
		
		jdbcCall.withSchemaName("SCOTT")
			    .withCatalogName("SHOPPING")
			    .withProcedureName("DELETE_PRODUCT");
			
		jdbcCall.addDeclaredParameter(new SqlParameter("PROD_ID", OracleTypes.NUMBER));
		jdbcCall.addDeclaredParameter(new SqlParameter("PROD_NAME", OracleTypes.VARCHAR));
		
		jdbcCall.execute(prod_id, prod_name);
		
	}

	private Product mapRowToProduct(ResultSet rs, int rowNum) throws SQLException {
		
		return new Product(rs.getInt("PROD_ID"), rs.getInt("CAT_ID"), rs.getString("PROD_NAME"), rs.getInt("PRICE"), rs.getInt("STOCK"), rs.getString("VENDOR"));
	}
}
