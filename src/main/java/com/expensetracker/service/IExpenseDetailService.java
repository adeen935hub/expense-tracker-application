package com.expensetracker.service;

import com.expensetracker.common.dto.request.ExpenseDetailRequest;
import com.expensetracker.common.dto.response.ExpenseDetailResponse;
import com.expensetracker.common.interfaces.IService;
import com.expensetracker.domain.ExpenseDetail;

public interface IExpenseDetailService extends IService<ExpenseDetail> {

	ExpenseDetailResponse domainToDto(ExpenseDetail expenseDetail);

	ExpenseDetail dtoToDomain(ExpenseDetailRequest req);

	Object getExpensesMonthYearWise();

	Object getExpensesCategoryMonthYearWise();

	Object getExpensesGroupedByCategoryWithSubcategory();

}
