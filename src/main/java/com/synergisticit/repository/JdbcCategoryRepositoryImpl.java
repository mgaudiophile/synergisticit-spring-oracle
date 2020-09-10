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

import com.synergisticit.domain.Category;

import oracle.jdbc.OracleTypes;

@Repository
public class JdbcCategoryRepositoryImpl implements CategoryRepository {

	private JdbcTemplate jdbc;
	
	@Autowired
	public JdbcCategoryRepositoryImpl(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Category> findAll() {
		System.out.println("JdbcCategoryRepositoryImpl.findAll()................");
		
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbc);
		
		jdbcCall.withSchemaName("SCOTT")
			    .withCatalogName("SHOPPING")
			    .withProcedureName("SELECT_CATEGORY")
			    .returningResultSet("RC", this::mapRowToCategory);
			
		Map<String, Object> result = jdbcCall.execute(new HashMap<String, Object>());
		
		return (List<Category>) result.get("RC");
	}
	
	@Override
	public Category save(Category category) {
		System.out.println("JdbcCategoryRepositoryImpl.save()................");
		
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbc);
		
		jdbcCall.withSchemaName("SCOTT")
			    .withCatalogName("SHOPPING")
			    .withProcedureName("INSERT_CATEGORY");
			
		jdbcCall.addDeclaredParameter(new SqlParameter("CAT_NAME", OracleTypes.VARCHAR));
		
		jdbcCall.execute(category.getCat_name());
		
		return category;
	}
	
	@Override
	public Category update(Category category) {
		System.out.println("JdbcCategoryRepositoryImpl.update()................");
		
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbc);
		
		jdbcCall.withSchemaName("SCOTT")
			    .withCatalogName("SHOPPING")
			    .withProcedureName("UPDATE_CATEGORY");
			
		jdbcCall.addDeclaredParameter(new SqlParameter("CAT_ID, CAT_NAME", OracleTypes.NUMBER, OracleTypes.VARCHAR));
		
		jdbcCall.execute(category.getCat_id(), category.getCat_name());
		
		return category;
	}
	
	@Override
	public void delete(int cat_id) {
		System.out.println("JdbcCategoryRepositoryImpl.delete()................");
		
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbc);
		
		jdbcCall.withSchemaName("SCOTT")
			    .withCatalogName("SHOPPING")
			    .withProcedureName("DELETE_CATEGORY");
			
		jdbcCall.addDeclaredParameter(new SqlParameter("CAT_ID", OracleTypes.NUMBER));
		
		jdbcCall.execute(cat_id);
	}
	
	private Category mapRowToCategory(ResultSet rs, int rowNum) throws SQLException {
		
		return new Category(rs.getInt("CAT_ID"), rs.getString("CAT_NAME"));
	}
}
