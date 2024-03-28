package com.expensetracker.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.expensetracker.common.dto.request.ExpenseDetailRequest;
import com.expensetracker.config.CustomApplicationContextInitializer;
import com.expensetracker.constants.WebAppConstant;
import com.expensetracker.domain.Category;
import com.expensetracker.domain.ExpenseDetail;
import com.expensetracker.domain.SubCategory;

@SpringBootTest
@ContextConfiguration(initializers = CustomApplicationContextInitializer.class)
@Transactional
class ExpenseServiceTest {
	@Autowired
	private ICategoryService categoryService;

	@Autowired
	private ISubCategoryService subCategoryService;

	@Autowired
	private IExpenseDetailService expenseDetailService;

	@BeforeEach
	public void setUp() {
		// Create categories required for testing sub-categories

		// Creating Housing category
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

	@Test
	public void create() {
		// Test creating expense details

		// Prepare expense detail request
		ExpenseDetailRequest expenseDetailRequest = new ExpenseDetailRequest();
		Category housingCategory = categoryService.findByCategoryName("Housing");
		SubCategory rentSubCategory = subCategoryService.findBySubCategoryName("rent");
		expenseDetailRequest.setCategoryId(housingCategory.getId());
		expenseDetailRequest.setSubCategoryId(rentSubCategory.getId());
		String expenseDateStr = "02-03-2024";
		Date expenseDate = new Date();
		try {
			expenseDate = WebAppConstant.REQUEST_DATE_FORMATOR.parse(expenseDateStr);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		expenseDetailRequest.setExpenseDate(expenseDate);

		// Convert DTO to domain object
		ExpenseDetail expenseDetail = expenseDetailService.dtoToDomain(expenseDetailRequest);

		// Create expense detail
		expenseDetailService.create(expenseDetail);

		// Check if the expense detail is created successfully
		List<ExpenseDetail> expenseDetails = expenseDetailService.findAll();
		assertThat(expenseDetails.size(), equalTo(1));

		// Check the details of the created expense detail
		ExpenseDetail fetchedData = expenseDetails.get(0);
		assertThat(fetchedData.getCategory(), equalTo(housingCategory));
		assertThat(fetchedData.getSubCategory(), equalTo(rentSubCategory));
		assertThat(fetchedData.getExpenseDate(), equalTo(expenseDate));
		assertThat(fetchedData.getId(), equalTo(1L));
	}

	@Test
	public void update() {
		// Test updating expense details

		// Prepare expense detail request
		ExpenseDetailRequest expenseDetailRequest = new ExpenseDetailRequest();
		Category housingCategory = categoryService.findByCategoryName("Housing");
		SubCategory rentSubCategory = subCategoryService.findBySubCategoryName("rent");
		BigDecimal expenseAmount = new BigDecimal(15000);
		expenseDetailRequest.setCategoryId(housingCategory.getId());
		expenseDetailRequest.setExpenseAmount(expenseAmount);
		expenseDetailRequest.setSubCategoryId(rentSubCategory.getId());
		String expenseDateStr = "02-03-2024";
		Date expenseDate = new Date();
		try {
			expenseDate = WebAppConstant.REQUEST_DATE_FORMATOR.parse(expenseDateStr);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		expenseDetailRequest.setExpenseDate(expenseDate);

		// Convert DTO to domain object
		ExpenseDetail expenseDetail = expenseDetailService.dtoToDomain(expenseDetailRequest);

		// Create expense detail
		expenseDetailService.create(expenseDetail);

		// Check if the expense detail is created successfully
		List<ExpenseDetail> expenseDetails = expenseDetailService.findAll();
		assertThat(expenseDetails.size(), equalTo(1));

		// Update expense detail
		ExpenseDetail fetchedData = expenseDetails.get(0);
		BigDecimal expenseNewAmount = new BigDecimal(10000);
		fetchedData.setExpenseAmount(expenseNewAmount);
		expenseDetailService.update(fetchedData);

		// Check if the expense detail is updated successfully
		ExpenseDetail newEditData = expenseDetailService.findOne(fetchedData.getId());
		assertThat(newEditData.getCategory(), equalTo(housingCategory));
		assertThat(newEditData.getExpenseAmount(), equalTo(expenseNewAmount));
		assertThat(newEditData.getSubCategory(), equalTo(rentSubCategory));
		assertThat(newEditData.getExpenseDate(), equalTo(expenseDate));
		assertThat(newEditData.getId(), equalTo(2L)); // Assuming the ID is updated to 2 after update
	}
}
