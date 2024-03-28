package com.expensetracker.common.dto.response;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class RetrievingExpensesCategoryWithSubcategoryResponse {

	private BigDecimal total;
	private String categoryName;
	private Map<String, BigDecimal> subCategory;

	// Constructor
	public RetrievingExpensesCategoryWithSubcategoryResponse() {
		this.total = BigDecimal.ZERO;
		this.subCategory = new HashMap<>();
	}

	// Getters and setters
	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	// Method to add year-month data
	public void addYearMonthData(String subCategoryName, BigDecimal amount) {
		this.total = this.total.add(amount);

		if (subCategoryName != null) {
			BigDecimal subCategoryAmount = this.subCategory.getOrDefault(subCategoryName, BigDecimal.ZERO);

			this.subCategory.put(subCategoryName, subCategoryAmount.add(amount));
		}

	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Map<String, BigDecimal> getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(Map<String, BigDecimal> subCategory) {
		this.subCategory = subCategory;
	}

}
