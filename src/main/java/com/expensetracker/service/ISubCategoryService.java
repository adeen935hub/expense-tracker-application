package com.expensetracker.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.expensetracker.common.dto.request.SubCategoryRequest;
import com.expensetracker.common.dto.response.SubCategoryResponse;
import com.expensetracker.common.interfaces.IService;
import com.expensetracker.domain.SubCategory;

public interface ISubCategoryService extends IService<SubCategory> {

	List<SubCategoryResponse> domainToDto(List<SubCategory> subCategories);

	SubCategoryResponse domainToDto(SubCategory subCategory);

	Page<SubCategoryResponse> domainToDto(Page<SubCategory> pages);

	SubCategory dtoToDomain(SubCategoryRequest req);

	SubCategoryResponse partialUpdate(Map<String, Object> req);

	SubCategory findBySubCategoryName(String subCategoryName);

}
