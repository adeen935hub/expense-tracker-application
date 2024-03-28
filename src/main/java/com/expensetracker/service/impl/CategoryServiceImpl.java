package com.expensetracker.service.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expensetracker.common.dto.request.CategoryRequest;
import com.expensetracker.common.dto.response.CategoryResponse;
import com.expensetracker.common.persistence.service.AbstractRawService;
import com.expensetracker.domain.Category;
import com.expensetracker.repository.CategoryRepository;
import com.expensetracker.service.ICategoryService;
import com.expensetracker.service.ISubCategoryService;

@Service
@Transactional
public class CategoryServiceImpl extends AbstractRawService<Category> implements ICategoryService {
	@Autowired
	private CategoryRepository repo;

	@Autowired
	private ISubCategoryService subCategoryService;

	@Override
	protected JpaRepository<Category, Long> getDao() {
		return repo;
	}

	@Override
	protected JpaSpecificationExecutor<Category> getSpecificationExecutor() {
		return repo;
	}

	@Override
	public Page<CategoryResponse> domainToDto(Page<Category> pages) {

		return pages.map(this::domainToDto);
	}

	@Override
	public List<CategoryResponse> domainToDto(List<Category> categories) {

		return categories.stream().map(this::domainToDto).collect(Collectors.toList());
	}

	@Override
	public CategoryResponse domainToDto(Category category) {
		return domainToDto(category, false);
	}

	@Override
	public CategoryResponse partialUpdate(Map<String, Object> partialData) {
		Long id = Long.valueOf(partialData.get("id").toString());
		Category category = findOne(id);
		for (Map.Entry<String, Object> entry : partialData.entrySet()) {
			String fieldName = entry.getKey();
			Object value = entry.getValue();
			if (!"id".equals(fieldName)) {
				try {
					Field field = Category.class.getDeclaredField(fieldName);
					field.setAccessible(true);
					field.set(category, value);
				} catch (NoSuchFieldException | IllegalAccessException e) {
					throw new IllegalArgumentException("Error updating field: " + fieldName, e);
				}
			}
		}

		return domainToDto(category);
	}

	@Override
	public Category dtoToDomain(CategoryRequest categoryReq) {
		Category category = new Category();
		if (categoryReq.getId() != null) {
			category = findOne(categoryReq.getId());
		}
		category.setCategoryDescription(categoryReq.getCategoryDescription());
		category.setCategoryName(categoryReq.getCategoryName());
		return category;
	}

	@Override
	public CategoryResponse domainToDto(Category category, Boolean ignoreSubCategory) {
		try {
			CategoryResponse categoryResponse = new CategoryResponse();
			categoryResponse.setCategoryDescription(category.getCategoryDescription());
			categoryResponse.setCategoryName(category.getCategoryName());
			categoryResponse.setId(category.getId());
			if (!ignoreSubCategory) {
				categoryResponse.setSubCategories(subCategoryService.domainToDto(category.getSubCategories()));
			}
			return categoryResponse;
		} catch (NullPointerException e) {
			// In case category can be null on .that time we will return null
			// Why we using try catch , it reduce unwanted null check for every time
			return null;
		}
	}
}
