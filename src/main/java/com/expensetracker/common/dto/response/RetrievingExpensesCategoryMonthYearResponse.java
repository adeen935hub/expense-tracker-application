package com.expensetracker.common.dto.response;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class RetrievingExpensesCategoryMonthYearResponse {

	private BigDecimal total;
	private String categoryName;
	private Map<String, Map<String, BigDecimal>> yearData;

	// Constructor
	public RetrievingExpensesCategoryMonthYearResponse() {
		this.total = BigDecimal.ZERO;
		this.yearData = new HashMap<>();
	}

	// Getters and setters
	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Map<String, Map<String, BigDecimal>> getYearData() {
		return yearData;
	}

	public void setYearData(Map<String, Map<String, BigDecimal>> yearData) {
		this.yearData = yearData;
	}

	// Method to add year-month data
	public void addYearMonthData(String year, String month, BigDecimal amount) {
		this.total = this.total.add(amount);

		Map<String, BigDecimal> monthData = this.yearData.getOrDefault(year, new HashMap<>());
		monthData.put(month, amount);
		this.yearData.put(year, monthData);
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
