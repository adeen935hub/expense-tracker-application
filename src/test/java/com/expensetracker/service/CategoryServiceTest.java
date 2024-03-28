package com.expensetracker.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.expensetracker.config.CustomApplicationContextInitializer;
import com.expensetracker.domain.Category;

@SpringBootTest
@ContextConfiguration(initializers = CustomApplicationContextInitializer.class)
@Transactional
class CategoryServiceTest {
	@Autowired
	private ICategoryService categoryService;

	private Category getCategoryData() {
		Category category = new Category();
		category.setCategoryDescription("Housing Description");
		category.setCategoryName("Housing");
		return category;
	}

	@Test
	public void create() {
		Category newCategory = getCategoryData();
		categoryService.create(newCategory);
		List<Category> result = categoryService.findAll();
		assertThat(result.size(), equalTo(1));
		Category fetchedCategory = result.get(0);

		assertThat(result.size(), equalTo(1));
		assertThat(fetchedCategory.getCategoryDescription(), equalTo(newCategory.getCategoryDescription()));
		assertThat(fetchedCategory.getCategoryName(), equalTo(newCategory.getCategoryName()));
		assertThat(fetchedCategory.getId(), equalTo(1L));
		assertThat(fetchedCategory.getSubCategories().size(), equalTo(0));
	}
}
