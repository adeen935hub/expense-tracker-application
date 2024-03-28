package com.expensetracker.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expensetracker.common.dto.request.ExpenseDetailRequest;
import com.expensetracker.common.dto.response.ExpenseDetailResponse;
import com.expensetracker.common.dto.response.RetrievingExpensesCategoryMonthYearResponse;
import com.expensetracker.common.dto.response.RetrievingExpensesCategoryWithSubcategoryResponse;
import com.expensetracker.common.dto.response.RetrievingExpensesMonthYearResponse;
import com.expensetracker.common.persistence.exception.CustomErrorHandleException;
import com.expensetracker.common.persistence.service.AbstractRawService;
import com.expensetracker.domain.Category;
import com.expensetracker.domain.ExpenseDetail;
import com.expensetracker.domain.SubCategory;
import com.expensetracker.repository.ExpenseDetailRepository;
import com.expensetracker.service.ICategoryService;
import com.expensetracker.service.IExpenseDetailService;
import com.expensetracker.service.ISubCategoryService;

@Service
@Transactional
public class ExpenseDetailServiceImpl extends AbstractRawService<ExpenseDetail> implements IExpenseDetailService {
	@Autowired
	private ExpenseDetailRepository repo;

	@Autowired
	private ICategoryService categoryService;

	@Autowired
	private ISubCategoryService subCategoryService;

	@Override
	protected JpaRepository<ExpenseDetail, Long> getDao() {
		return repo;
	}

	@Override
	protected JpaSpecificationExecutor<ExpenseDetail> getSpecificationExecutor() {
		return repo;
	}

	@Override
	public ExpenseDetailResponse domainToDto(ExpenseDetail expenseDetail) {
		try {
			ExpenseDetailResponse expenseResponse = new ExpenseDetailResponse();
			expenseResponse.setId(expenseDetail.getId());
			expenseResponse.setCategory(categoryService.domainToDto(expenseDetail.getCategory(), true));
			expenseResponse.setSubCategory(subCategoryService.domainToDto(expenseDetail.getSubCategory()));
			expenseResponse.setExpenseAmount(expenseDetail.getExpenseAmount());
			expenseResponse.setExpenseDate(expenseDetail.getExpenseDate());
			return expenseResponse;
		} catch (NullPointerException e) {
			// In case Expense Detail can be null on .that time we will return null
			// Why we using try catch , it reduce unwanted null check for every time
			return null;
		}
	}

	@Override
	public ExpenseDetail dtoToDomain(ExpenseDetailRequest req) {
		ExpenseDetail expenseDetail = new ExpenseDetail();
		if (req.getId() != null) {
			expenseDetail = findOne(req.getId());
		}
		expenseDetail.setExpenseAmount(req.getExpenseAmount());
		expenseDetail.setExpenseDate(req.getExpenseDate());
		if (req.getSubCategoryId() != null) {
			SubCategory subCateogry = subCategoryService.findOne(req.getSubCategoryId());

			if (subCateogry == null) {
				throw new CustomErrorHandleException("Unable to find a subcategory.");
			} else if (req.getCategoryId() != subCateogry.getCategory().getId()) {
				throw new CustomErrorHandleException("Subcategory and Category is mismatching");
			} else {
				expenseDetail.setSubCategory(subCateogry);
				expenseDetail.setCategory(subCateogry.getCategory());
			}
		} else {
			expenseDetail.setCategory(categoryService.findOne(req.getCategoryId()));
		}

		return expenseDetail;
	}

	@Override
	public RetrievingExpensesMonthYearResponse getExpensesMonthYearWise() {
		List<Object[]> resultList = repo.getExpensesMonthYearWise();

		RetrievingExpensesMonthYearResponse retrievingExpensesYearResponse = new RetrievingExpensesMonthYearResponse();
		for (Object[] row : resultList) {
			String year = row[0].toString();
			String month = row[1].toString();
			BigDecimal amount = (BigDecimal) row[2];

			retrievingExpensesYearResponse.addYearMonthData(year, month, amount);
		}

		return retrievingExpensesYearResponse;
	}

	@Override
	public Object getExpensesCategoryMonthYearWise() {
		List<Object[]> resultList = repo.getExpensesCategoryMonthYearWise();
		Map<String, RetrievingExpensesCategoryMonthYearResponse> responseMap = new HashMap<>();

		for (Object[] row : resultList) {
			Integer year = (Integer) row[0];
			Integer month = (Integer) row[1];
			BigDecimal amount = (BigDecimal) row[2];
			String categoryName = (String) row[3];

			String yearStr = String.valueOf(year);
			String monthStr = String.valueOf(month);

			RetrievingExpensesCategoryMonthYearResponse response = responseMap.computeIfAbsent(categoryName,
					k -> new RetrievingExpensesCategoryMonthYearResponse());
			response.setCategoryName(categoryName);
			response.addYearMonthData(yearStr, monthStr, amount);
		}

		return new ArrayList<>(responseMap.values());
	}

	@Override
	public Object getExpensesGroupedByCategoryWithSubcategory() {
		Map<Category, List<ExpenseDetail>> expenseDetailGroupByCategory = findAll().stream()
				.collect(Collectors.groupingBy(ExpenseDetail::getCategory));

		List<RetrievingExpensesCategoryWithSubcategoryResponse> result = new ArrayList<>();

		for (Map.Entry<Category, List<ExpenseDetail>> entry : expenseDetailGroupByCategory.entrySet()) {
			Category category = entry.getKey();
			List<ExpenseDetail> categoryExpenses = entry.getValue();

			RetrievingExpensesCategoryWithSubcategoryResponse response = new RetrievingExpensesCategoryWithSubcategoryResponse();
			response.setCategoryName(category.getCategoryName());

			for (ExpenseDetail expenseDetail : categoryExpenses) {
				String subCategoryName = (expenseDetail.getSubCategory() != null)
						? expenseDetail.getSubCategory().getSubCategoryName()
						: null;
				response.addYearMonthData(subCategoryName, expenseDetail.getExpenseAmount());
			}

			result.add(response);
		}

		return result;
	}

}
