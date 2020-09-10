package com.synergisticit.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Struct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.synergisticit.domain.Address;
import com.synergisticit.domain.Customer;

import oracle.jdbc.OracleTypes;

@Repository
public class JdbcCustomerRepositoryImpl implements CustomerRepository {

	private JdbcTemplate jdbc;
	
	@Autowired
	public JdbcCustomerRepositoryImpl(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> findAll() {
		System.out.println("JdbcCustomerRepositoryImpl.findAll()................");
		
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbc);
		
		jdbcCall.withSchemaName("SCOTT")
			    .withCatalogName("SHOPPING")
			    .withProcedureName("SELECT_CUSTOMER")
			    .returningResultSet("RC", this::mapRowToCustomer);
			
		Map<String, Object> result = jdbcCall.execute(new HashMap<String, Object>());
		
		return (List<Customer>) result.get("RC");
	}

	@Override
	public Customer save(Customer customer) {
		System.out.println("JdbcCustomerRepositoryImpl.save()................");
		
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbc);
		
		jdbcCall.withSchemaName("SCOTT")
			    .withCatalogName("SHOPPING")
			    .withProcedureName("INSERT_CUSTOMER");
			
		jdbcCall.addDeclaredParameter(new SqlParameter("FIRST_NAME", OracleTypes.VARCHAR));
		jdbcCall.addDeclaredParameter(new SqlParameter("LAST_NAME", OracleTypes.VARCHAR));
		jdbcCall.addDeclaredParameter(new SqlParameter("STREET", OracleTypes.VARCHAR));
		jdbcCall.addDeclaredParameter(new SqlParameter("CITY", OracleTypes.VARCHAR));
		jdbcCall.addDeclaredParameter(new SqlParameter("STATE", OracleTypes.VARCHAR));
		jdbcCall.addDeclaredParameter(new SqlParameter("ZIP", OracleTypes.VARCHAR));
		jdbcCall.addDeclaredParameter(new SqlParameter("MOBILE", OracleTypes.NUMBER));
		
		jdbcCall.execute(customer.getFirst_name(), customer.getLast_name(), 
				customer.getAddr().getStreet(), customer.getAddr().getCity(), 
				customer.getAddr().getState(), customer.getAddr().getZip(), customer.getMobile());
		
		return customer;
	}

	@Override
	public Customer update(Customer customer) {
		System.out.println("JdbcCustomerRepositoryImpl.update()................");
		
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbc);
		
		jdbcCall.withSchemaName("SCOTT")
			    .withCatalogName("SHOPPING")
			    .withProcedureName("UPDATE_CUSTOMER");
			
		jdbcCall.addDeclaredParameter(new SqlParameter("CUSTOMER_ID", OracleTypes.NUMBER));
		jdbcCall.addDeclaredParameter(new SqlParameter("FIRST_NAME", OracleTypes.VARCHAR));
		jdbcCall.addDeclaredParameter(new SqlParameter("LAST_NAME", OracleTypes.VARCHAR));
		jdbcCall.addDeclaredParameter(new SqlParameter("STREET", OracleTypes.VARCHAR));
		jdbcCall.addDeclaredParameter(new SqlParameter("CITY", OracleTypes.VARCHAR));
		jdbcCall.addDeclaredParameter(new SqlParameter("STATE", OracleTypes.VARCHAR));
		jdbcCall.addDeclaredParameter(new SqlParameter("ZIP", OracleTypes.VARCHAR));
		jdbcCall.addDeclaredParameter(new SqlParameter("MOBILE", OracleTypes.NUMBER));
		
		jdbcCall.execute(customer.getCustomer_id(), customer.getFirst_name(), customer.getLast_name(), 
				customer.getAddr().getStreet(), customer.getAddr().getCity(), 
				customer.getAddr().getState(), customer.getAddr().getZip(), customer.getMobile());
		
		return customer;
	}
	
	@Override
	public void delete(int customer_id, String first_name, String last_name) {
		System.out.println("JdbcCustomerRepositoryImpl.delete()................" + customer_id + " " + first_name + " " + last_name);
		
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbc);
		
		jdbcCall.withSchemaName("SCOTT")
			    .withCatalogName("SHOPPING")
			    .withProcedureName("DELETE_CUSTOMER");
			
		jdbcCall.addDeclaredParameter(new SqlParameter("CUSTOMER_ID", OracleTypes.NUMBER));
		jdbcCall.addDeclaredParameter(new SqlParameter("FIRST_NAME", OracleTypes.VARCHAR));
		jdbcCall.addDeclaredParameter(new SqlParameter("LAST_NAME", OracleTypes.VARCHAR));
		
		jdbcCall.execute(customer_id, first_name, last_name);
	}

	private Customer mapRowToCustomer(ResultSet rs, int rowNum) throws SQLException {
		
		Struct jdbcStruct = (java.sql.Struct) rs.getObject("ADDR");
		Object[] attrs = jdbcStruct.getAttributes();
		
		Address a = new Address();
		a.setStreet(attrs[0].toString());
		a.setCity(attrs[1].toString());
		a.setState(attrs[2].toString());
		a.setZip(attrs[3].toString());
		
		return new Customer(rs.getInt("CUSTOMER_ID"), rs.getString("FIRST_NAME"), rs.getString("LAST_NAME"), a, rs.getLong("MOBILE"));
	}
}
