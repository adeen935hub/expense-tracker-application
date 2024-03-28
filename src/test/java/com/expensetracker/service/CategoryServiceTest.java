package com.expensetracker.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.expensetracker.config.CustomApplicationContextInitializer;
import com.expensetracker.domain.Category;

@SpringBootTest
@ContextConfiguration(initializers = CustomApplicationContextInitializer.class)
@Transactional
class CategoryServiceTest {

	@Autowired
	private ICategoryService categoryService;

	// Test method to verify category creation functionality
	@Test
	public void create() {
		// Creating a new category
		Category newCategory = new Category();
		newCategory.setCategoryDescription("Housing Description");
		newCategory.setCategoryName("Housing");

		// Invoking category creation method
		categoryService.create(newCategory);

		// Verifying if the category was successfully created
		List<Category> categories = categoryService.findAll();
		assertThat(categories.size(), equalTo(1));
		Category fetchedCategory = categories.get(0);

		// Assertions to ensure correctness of the created category
		assertThat(categories.size(), equalTo(1));
		assertThat(fetchedCategory.getCategoryDescription(), equalTo(newCategory.getCategoryDescription()));
		assertThat(fetchedCategory.getCategoryName(), equalTo(newCategory.getCategoryName()));
		assertThat(fetchedCategory.getId(), equalTo(1L));
		assertThat(fetchedCategory.getSubCategories().size(), equalTo(0));
	}

	// Test method to verify category update functionality
	@Test
	public void update() {
		// Creating a new category
		Category newCategory = new Category();
		newCategory.setCategoryDescription("Transpotation");
		newCategory.setCategoryName("Transpotation");

		// Invoking category creation method
		categoryService.create(newCategory);

		// Verifying if the category was successfully created
		List<Category> categories = categoryService.findAll();
		assertThat(categories.size(), equalTo(1));

		// Retrieving the created category for update
		Category fetchedCategory = categories.get(0);
		// Modifying category name
		fetchedCategory.setCategoryName("Transpotation new");

		// Invoking category update method
		fetchedCategory = categoryService.update(fetchedCategory);

		// Retrieving the updated category
		Category fetchEditedCategory = categoryService.findOne(fetchedCategory.getId());

		// Assertions to ensure correctness of the updated category
		assertThat(fetchEditedCategory.getCategoryDescription(), equalTo(newCategory.getCategoryDescription()));
		assertThat(fetchEditedCategory.getCategoryName(), equalTo("Transpotation new"));
		assertThat(fetchEditedCategory.getId(), equalTo(2L));
		assertThat(fetchEditedCategory.getSubCategories().size(), equalTo(0));
	}
}
