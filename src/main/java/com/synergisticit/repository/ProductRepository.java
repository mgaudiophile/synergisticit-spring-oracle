package com.synergisticit.repository;

import java.util.List;

import com.synergisticit.domain.Product;

public interface ProductRepository {

	List<Product> findAll();
	Product save(Product product);
	Product update(Product product);
	void delete(int prod_id, String prod_name);
}
