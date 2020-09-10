package com.synergisticit.repository;

import java.util.List;

import com.synergisticit.domain.Order_Table;

public interface Order_TableRepository {

	List<Order_Table> findAll();
	Order_Table save(Order_Table order);
	Order_Table update(Order_Table order);
	void delete(int order_id, int customer_id);
}
