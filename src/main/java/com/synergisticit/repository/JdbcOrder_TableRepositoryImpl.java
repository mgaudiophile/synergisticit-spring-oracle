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

import com.synergisticit.domain.Order_Table;

import oracle.jdbc.OracleTypes;

@Repository
public class JdbcOrder_TableRepositoryImpl implements Order_TableRepository {

	private JdbcTemplate jdbc;
	
	@Autowired
	public JdbcOrder_TableRepositoryImpl(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Order_Table> findAll() {
		System.out.println("JdbcOrder_TableRepositoryImpl.findAll()................");
		
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbc);
		
		jdbcCall.withSchemaName("SCOTT")
			    .withCatalogName("SHOPPING")
			    .withProcedureName("SELECT_ORDER_TABLE")
			    .returningResultSet("RC", this::mapRowToOrder_Table);
			
		Map<String, Object> result = jdbcCall.execute(new HashMap<String, Object>());
		
		return (List<Order_Table>) result.get("RC");
	}

	@Override
	public Order_Table save(Order_Table order) {
		System.out.println("JdbcOrder_TableRepositoryImpl.save()................");
		
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbc);
		
		jdbcCall.withSchemaName("SCOTT")
			    .withCatalogName("SHOPPING")
			    .withProcedureName("INSERT_ORDER_TABLE");
			
		jdbcCall.addDeclaredParameter(new SqlParameter("CUSTOMER_ID", OracleTypes.NUMBER));
		jdbcCall.addDeclaredParameter(new SqlParameter("PROD_ID", OracleTypes.NUMBER));
		jdbcCall.addDeclaredParameter(new SqlParameter("ORDER_DATE", OracleTypes.DATE));
		jdbcCall.addDeclaredParameter(new SqlParameter("QUANTITY", OracleTypes.NUMBER));
		jdbcCall.addDeclaredParameter(new SqlParameter("ORDER_STATUS", OracleTypes.VARCHAR));
		
		jdbcCall.execute(order.getCustomer_id(), order.getProd_id(), 
						order.getOrder_date(), order.getQuantity(), 
						order.getOrder_status());
		
		return order;
	}

	@Override
	public Order_Table update(Order_Table order) {
		System.out.println("JdbcCustomerRepositoryImpl.update()................");
		
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbc);
		
		jdbcCall.withSchemaName("SCOTT")
			    .withCatalogName("SHOPPING")
			    .withProcedureName("UPDATE_ORDER_TABLE");
			
		jdbcCall.addDeclaredParameter(new SqlParameter("ORDER_ID", OracleTypes.NUMBER));
		jdbcCall.addDeclaredParameter(new SqlParameter("CUSTOMER_ID", OracleTypes.NUMBER));
		jdbcCall.addDeclaredParameter(new SqlParameter("PROD_ID", OracleTypes.NUMBER));
		jdbcCall.addDeclaredParameter(new SqlParameter("ORDER_DATE", OracleTypes.DATE));
		jdbcCall.addDeclaredParameter(new SqlParameter("QUANTITY", OracleTypes.NUMBER));
		jdbcCall.addDeclaredParameter(new SqlParameter("ORDER_STATUS", OracleTypes.VARCHAR));
		
		jdbcCall.execute(order.getOrder_id(), order.getCustomer_id(), order.getProd_id(), 
						order.getOrder_date(), order.getQuantity(), 
						order.getOrder_status());
		
		return order;
	}
	
	@Override
	public void delete(int order_id, int customer_id) {
		System.out.println("JdbcCustomerRepositoryImpl.delete()................" + order_id + " " + customer_id);
		
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbc);
		
		jdbcCall.withSchemaName("SCOTT")
			    .withCatalogName("SHOPPING")
			    .withProcedureName("DELETE_ORDER_TABLE");
			
		jdbcCall.addDeclaredParameter(new SqlParameter("ORDER_ID", OracleTypes.NUMBER));
		jdbcCall.addDeclaredParameter(new SqlParameter("CUSTOMER_ID", OracleTypes.NUMBER));
		
		jdbcCall.execute(order_id, customer_id);
	}

	private Order_Table mapRowToOrder_Table(ResultSet rs, int rowNum) throws SQLException {
		
		return new Order_Table(rs.getInt("ORDER_ID"), rs.getInt("CUSTOMER_ID"), rs.getInt("PROD_ID"), rs.getDate("ORDER_DATE"), rs.getInt("QUANTITY"), rs.getInt("TOTAL_PRICE"), rs.getString("ORDER_STATUS"));
	}
}
