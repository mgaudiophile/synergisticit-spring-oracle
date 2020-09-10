package com.synergisticit.repository;

import java.util.List;

import com.synergisticit.domain.Category;

public interface CategoryRepository {

	List<Category> findAll();
	Category save(Category category);
	Category update(Category category);
	void delete(int cat_id);
}
