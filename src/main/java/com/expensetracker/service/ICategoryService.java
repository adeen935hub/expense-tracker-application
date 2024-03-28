package com.expensetracker.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.expensetracker.common.dto.request.CategoryRequest;
import com.expensetracker.common.dto.response.CategoryResponse;
import com.expensetracker.common.interfaces.IService;
import com.expensetracker.domain.Category;

public interface ICategoryService extends IService<Category> {

	Page<CategoryResponse> domainToDto(Page<Category> pages);

	List<CategoryResponse> domainToDto(List<Category> categories);

	CategoryResponse domainToDto(Category category);
	CategoryResponse domainToDto(Category category,Boolean ignoreSubCategory);

	Object partialUpdate(Map<String, Object> req);

	Category dtoToDomain(CategoryRequest req);

}
