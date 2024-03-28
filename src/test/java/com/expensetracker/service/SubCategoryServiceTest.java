package com.expensetracker.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.expensetracker.config.CustomApplicationContextInitializer;
import com.expensetracker.domain.Category;
import com.expensetracker.domain.SubCategory;

@SpringBootTest
@ContextConfiguration(initializers = CustomApplicationContextInitializer.class)
@Transactional
class SubCategoryServiceTest {
	@Autowired
	private ICategoryService categoryService;

	@Autowired
	private ISubCategoryService subCategoryService;

	@BeforeEach
	public void setUp() {
		// Create categories required for testing sub-categories
		Category housingCategory = new Category();
		housingCategory.setCategoryDescription("Housing Description");
		housingCategory.setCategoryName("Housing");
		categoryService.create(housingCategory);

		// Creating a new subCategory
		SubCategory newSubCategory = new SubCategory();
		newSubCategory.setSubCategoryDescription("rent");
		newSubCategory.setSubCategoryName("rent");
		newSubCategory.setCategory(categoryService.findByCategoryName("Housing"));

		// Invoking subCategory creation method
		subCategoryService.create(newSubCategory);

	}

	// Test method to verify subCategory creation functionality
	@Test
	public void create() {
		// Verifying if the category was successfully created
		List<Category> categories = categoryService.findAll();
		assertThat(categories.size(), equalTo(1));

		// Verifying if the subCategory was successfully created
		List<SubCategory> subCategories = subCategoryService.findAll();
		assertThat(subCategories.size(), equalTo(1));
		SubCategory fetchedSubCategory = subCategories.get(0);

		// Assertions to ensure correctness of the created subCategory
		assertThat(subCategories.size(), equalTo(1));
		assertThat(fetchedSubCategory.getSubCategoryDescription(), equalTo("rent"));
		assertThat(fetchedSubCategory.getSubCategoryName(), equalTo("rent"));

	}

	// Test method to verify subCategory update functionality
	@Test
	public void update() {

		// Verifying if the subCategory was successfully created
		List<SubCategory> result = subCategoryService.findAll();
		assertThat(result.size(), equalTo(1));

		// Retrieving the created subCategory for update
		SubCategory fetchedSubCategory = result.get(0);
		// Modifying subCategory name
		fetchedSubCategory.setSubCategoryName("House Rent");

		// Invoking subCategory update method
		fetchedSubCategory = subCategoryService.update(fetchedSubCategory);

		// Retrieving the updated subCategory
		SubCategory fetchEditedSubCategory = subCategoryService.findOne(fetchedSubCategory.getId());

		// Assertions to ensure correctness of the updated subCategory
		assertThat(fetchEditedSubCategory.getSubCategoryDescription(), equalTo("rent"));
		assertThat(fetchEditedSubCategory.getSubCategoryName(), equalTo("House Rent"));

	}
}
