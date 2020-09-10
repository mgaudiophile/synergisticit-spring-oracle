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

import com.synergisticit.domain.Payment;

import oracle.jdbc.OracleTypes;

@Repository
public class JdbcPaymentRepositoryImpl implements PaymentRepository {

	private JdbcTemplate jdbc;
	
	@Autowired
	public JdbcPaymentRepositoryImpl(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Payment> findAll() {
		System.out.println("JdbcPaymentRepositoryImpl.findAll()................");
		
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbc);
		
		jdbcCall.withSchemaName("SCOTT")
			    .withCatalogName("SHOPPING")
			    .withProcedureName("SELECT_PAYMENT")
			    .returningResultSet("RC", this::mapRowToPayment);
			
		Map<String, Object> result = jdbcCall.execute(new HashMap<String, Object>());
		
		return (List<Payment>) result.get("RC");
	}

	@Override
	public Payment save(Payment payment) {
		System.out.println("JdbcPaymentRepositoryImpl.save()................");
		
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbc);
		
		jdbcCall.withSchemaName("SCOTT")
			    .withCatalogName("SHOPPING")
			    .withProcedureName("INSERT_PAYMENT");
			
		jdbcCall.addDeclaredParameter(new SqlParameter("ORDER_ID", OracleTypes.NUMBER));
		jdbcCall.addDeclaredParameter(new SqlParameter("STATUS", OracleTypes.VARCHAR));
		
		jdbcCall.execute(payment.getOrder_id(), payment.getStatus());
		
		return payment;
	}

	@Override
	public Payment update(Payment payment) {
		System.out.println("JdbcPaymentRepositoryImpl.update()................");
		
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbc);
		
		jdbcCall.withSchemaName("SCOTT")
			    .withCatalogName("SHOPPING")
			    .withProcedureName("UPDATE_PAYMENT");
		
		jdbcCall.addDeclaredParameter(new SqlParameter("PAYMENT_ID", OracleTypes.NUMBER));
		jdbcCall.addDeclaredParameter(new SqlParameter("ORDER_ID", OracleTypes.NUMBER));
		jdbcCall.addDeclaredParameter(new SqlParameter("STATUS", OracleTypes.VARCHAR));
		
		jdbcCall.execute(payment.getPayment_id(), payment.getOrder_id(), payment.getStatus());
		
		return payment;
	}
	
	@Override
	public void delete(int payment_id, int order_id) {
		System.out.println("JdbcPaymentRepositoryImpl.delete()................" + payment_id + " " + order_id);
		
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbc);
		
		jdbcCall.withSchemaName("SCOTT")
			    .withCatalogName("SHOPPING")
			    .withProcedureName("DELETE_PAYMENT");
			
		jdbcCall.addDeclaredParameter(new SqlParameter("PAYMENT_ID", OracleTypes.NUMBER));
		jdbcCall.addDeclaredParameter(new SqlParameter("ORDER_ID", OracleTypes.NUMBER));
		
		jdbcCall.execute(payment_id, order_id);
	}

	private Payment mapRowToPayment(ResultSet rs, int rowNum) throws SQLException {
		
		return new Payment(rs.getInt("PAYMENT_ID"), rs.getInt("ORDER_ID"), rs.getString("STATUS"));
	}
}
