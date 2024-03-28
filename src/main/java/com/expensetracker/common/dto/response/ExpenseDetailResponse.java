package com.expensetracker.common.dto.response;

import java.math.BigDecimal;
import java.util.Date;

import com.expensetracker.constants.WebAppConstant;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ExpenseDetailResponse {
	private Long id;
	private CategoryResponse category;
	private SubCategoryResponse subCategory;
	private BigDecimal expenseAmount;
	@JsonFormat(pattern = WebAppConstant.RESPONSE_DATE_FORMAT)
	private Date expenseDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CategoryResponse getCategory() {
		return category;
	}

	public void setCategory(CategoryResponse category) {
		this.category = category;
	}

	public SubCategoryResponse getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategoryResponse subCategory) {
		this.subCategory = subCategory;
	}

	public BigDecimal getExpenseAmount() {
		return expenseAmount;
	}

	public void setExpenseAmount(BigDecimal expenseAmount) {
		this.expenseAmount = expenseAmount;
	}

	public Date getExpenseDate() {
		return expenseDate;
	}

	public void setExpenseDate(Date expenseDate) {
		this.expenseDate = expenseDate;
	}
}
