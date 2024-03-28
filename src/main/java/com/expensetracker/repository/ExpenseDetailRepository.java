package com.expensetracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.expensetracker.domain.ExpenseDetail;

@Repository
public interface ExpenseDetailRepository
		extends JpaRepository<ExpenseDetail, Long>, JpaSpecificationExecutor<ExpenseDetail> {

	@Query("SELECT YEAR(e.expenseDate), MONTH(e.expenseDate), SUM(e.expenseAmount) " + "FROM ExpenseDetail e "
			+ "GROUP BY YEAR(e.expenseDate), MONTH(e.expenseDate)")
	List<Object[]> getExpensesMonthYearWise();

	@Query("SELECT YEAR(e.expenseDate), MONTH(e.expenseDate), SUM(e.expenseAmount), e.category.categoryName "
			+ "FROM ExpenseDetail e " + "GROUP BY e.category.categoryName, YEAR(e.expenseDate), MONTH(e.expenseDate)")
	List<Object[]> getExpensesCategoryMonthYearWise();

}
