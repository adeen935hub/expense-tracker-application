package com.expensetracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.expensetracker.common.dto.request.ExpenseDetailRequest;
import com.expensetracker.common.web.CommonResponse;
import com.expensetracker.domain.ExpenseDetail;
import com.expensetracker.service.IExpenseDetailService;

@RestController
@RequestMapping(value = "expense-detail")
public class ExpenseDetailController {
	@Autowired
	private IExpenseDetailService expenseDetailService;

	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<CommonResponse> update(@RequestBody final ExpenseDetailRequest req) {

		ExpenseDetail expenseDetail = expenseDetailService.dtoToDomain(req);
		expenseDetail = expenseDetailService.update(expenseDetail);

		CommonResponse res = new CommonResponse();
		res.setStatus(HttpStatus.OK.value());
		res.setData(expenseDetailService.domainToDto(expenseDetail));
		res.setMessage("Successfully Updated expenseDetail...!");
		return new ResponseEntity<CommonResponse>(res, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<CommonResponse> delete(@PathVariable Long id) {

		CommonResponse res = new CommonResponse();
		res.setStatus(HttpStatus.OK.value());
		expenseDetailService.delete(id);
		res.setMessage("Successfully Delete Expense Detail...!");
		return new ResponseEntity<CommonResponse>(res, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<CommonResponse> create(@RequestBody final ExpenseDetailRequest req) {

		ExpenseDetail expenseDetail = expenseDetailService.dtoToDomain(req);
		expenseDetail = expenseDetailService.create(expenseDetail);

		CommonResponse res = new CommonResponse();
		res.setStatus(HttpStatus.OK.value());
		res.setData(expenseDetailService.domainToDto(expenseDetail));
		res.setMessage("Successfully Created expenseDetail...!");
		return new ResponseEntity<CommonResponse>(res, HttpStatus.OK);

	}

	@RequestMapping(value = "/get-expenses-month-year-wise", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<CommonResponse> getExpensesMonthYearWise() {

		CommonResponse res = new CommonResponse();
		res.setStatus(HttpStatus.OK.value());
		res.setData(expenseDetailService.getExpensesMonthYearWise());
		res.setMessage("Successfully retrieving expenses date wise...!");
		return new ResponseEntity<CommonResponse>(res, HttpStatus.OK);

	}

	@RequestMapping(value = "/get-expenses-category-month-year-wise", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<CommonResponse> getExpensesCategoryMonthYearWise() {

		CommonResponse res = new CommonResponse();
		res.setStatus(HttpStatus.OK.value());
		res.setData(expenseDetailService.getExpensesCategoryMonthYearWise());
		res.setMessage("Successfully retrieving expenses date wise...!");
		return new ResponseEntity<CommonResponse>(res, HttpStatus.OK);

	}

	@RequestMapping(value = "/get-expenses-category-with-subcategory-wise", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<CommonResponse> getExpensesGroupedByCategoryWithSubcategory() {

		CommonResponse res = new CommonResponse();
		res.setStatus(HttpStatus.OK.value());
		res.setData(expenseDetailService.getExpensesGroupedByCategoryWithSubcategory());
		res.setMessage("Successfully retrieving expenses date wise...!");
		return new ResponseEntity<CommonResponse>(res, HttpStatus.OK);

	}

}
