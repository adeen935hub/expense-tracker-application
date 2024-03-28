package com.expensetracker.common.dto.response;

import java.util.ArrayList;
import java.util.List;

public class CategoryResponse {
	private Long id;

	private String categoryName;

	private String categoryDescription;

	private List<SubCategoryResponse> subCategories = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	public List<SubCategoryResponse> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(List<SubCategoryResponse> subCategories) {
		this.subCategories = subCategories;
	}
}
