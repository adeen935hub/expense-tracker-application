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

import com.expensetracker.common.dto.request.SubCategoryRequest;
import com.expensetracker.common.dto.response.SubCategoryResponse;
import com.expensetracker.common.persistence.service.AbstractRawService;
import com.expensetracker.domain.Category;
import com.expensetracker.domain.SubCategory;
import com.expensetracker.repository.SubCategoryRepository;
import com.expensetracker.service.ICategoryService;
import com.expensetracker.service.ISubCategoryService;

@Service
@Transactional
public class SubCategoryServiceImpl extends AbstractRawService<SubCategory> implements ISubCategoryService {
	@Autowired
	private SubCategoryRepository repo;
	@Autowired
	private ICategoryService categoryService;

	@Override
	protected JpaRepository<SubCategory, Long> getDao() {
		return repo;
	}

	@Override
	protected JpaSpecificationExecutor<SubCategory> getSpecificationExecutor() {
		return repo;
	}

	@Override
	public List<SubCategoryResponse> domainToDto(List<SubCategory> subCategories) {

		return subCategories.stream().map(this::domainToDto).collect(Collectors.toList());
	}

	@Override
	public SubCategoryResponse domainToDto(SubCategory subCategory) {
		try {
			SubCategoryResponse subCategoryResponse = new SubCategoryResponse();
			subCategoryResponse.setSubCategoryDescription(subCategory.getSubCategoryDescription());
			subCategoryResponse.setSubCategoryName(subCategory.getSubCategoryName());
			subCategoryResponse.setId(subCategory.getId());
			subCategoryResponse.setCategoryId(subCategory.getCategory().getId());
			return subCategoryResponse;
		} catch (NullPointerException e) {
			// In case Sub Category can be null on .that time we will return null
			// Why we using try catch , it reduce unwanted null check for every time
			return null;
		}
	}

	@Override
	public Page<SubCategoryResponse> domainToDto(Page<SubCategory> pages) {
		return pages.map(this::domainToDto);
	}

	@Override
	public SubCategory dtoToDomain(SubCategoryRequest req) {
		SubCategory subCategory = new SubCategory();
		if (req.getId() != null) {
			subCategory = findOne(req.getId());
		}
		subCategory.setSubCategoryDescription(req.getSubCategoryDescription());
		subCategory.setSubCategoryName(req.getSubCategoryName());
		if (req.getCategoryId() != null) {
			Category category = categoryService.findOne(req.getCategoryId());
			subCategory.setCategory(category);
		}
		return subCategory;
	}

	@Override
	public SubCategoryResponse partialUpdate(Map<String, Object> partialData) {
		Long id = Long.valueOf(partialData.get("id").toString());
		SubCategory subCategory = findOne(id);
		for (Map.Entry<String, Object> entry : partialData.entrySet()) {
			String fieldName = entry.getKey();
			Object value = entry.getValue();
			if (!"id".equals(fieldName)) {

				try {
					if ("categoryId".equals(fieldName)) {
						Field field = SubCategory.class.getDeclaredField("category");
						field.setAccessible(true);
						Category category = categoryService.findOne(Long.parseLong(value.toString()));
						field.set(subCategory, category);
					} else {
						Field field = SubCategory.class.getDeclaredField(fieldName);
						field.setAccessible(true);
						field.set(subCategory, value);
					}

				} catch (NoSuchFieldException | IllegalAccessException e) {
					throw new IllegalArgumentException("Error updating field: " + fieldName, e);
				}
			}
		}

		return domainToDto(subCategory);
	}

	@Override
	public SubCategory findBySubCategoryName(String subCategoryName) {
		// TODO Auto-generated method stub
		return repo.findBySubCategoryName(subCategoryName);
	}

}
