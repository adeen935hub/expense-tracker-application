package com.expensetracker.common.dto.request;

import java.math.BigDecimal;
import java.util.Date;

import com.expensetracker.constants.WebAppConstant;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ExpenseDetailRequest {

	private Long id;

	private Long categoryId;

	private Long subCategoryId;

	private BigDecimal expenseAmount;

	@JsonFormat(pattern = WebAppConstant.REQUEST_DATE_FORMAT)
	private Date expenseDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(Long subCategoryId) {
		this.subCategoryId = subCategoryId;
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
