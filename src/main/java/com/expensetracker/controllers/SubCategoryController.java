package com.expensetracker.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.expensetracker.common.dto.FilterDto;
import com.expensetracker.common.dto.request.SubCategoryRequest;
import com.expensetracker.common.web.CommonResponse;
import com.expensetracker.domain.SubCategory;
import com.expensetracker.service.ISubCategoryService;

@RestController
@RequestMapping(value = "sub-category")
public class SubCategoryController {
	@Autowired
	private ISubCategoryService subCategoryService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<CommonResponse> findAll(@RequestParam(required = false) Integer size,
			@RequestParam(required = false) Integer pageNo) {
		CommonResponse res = new CommonResponse();
		res.setStatus(HttpStatus.OK.value());
		res.setMessage("Successfully fetched subCategory Data..");
		if (size != null & pageNo != null) {
			FilterDto filter = new FilterDto();
			Page<SubCategory> pages = subCategoryService.findAll(size, pageNo, filter);
			res.setData(subCategoryService.domainToDto(pages));
		} else {
			res.setData(subCategoryService.domainToDto(subCategoryService.findAll()));
		}
		return new ResponseEntity<CommonResponse>(res, HttpStatus.OK);

	}

	@RequestMapping(value = "/filter", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<CommonResponse> findAll(@RequestParam(required = false) Integer size,
			@RequestParam(required = false) Integer pageNo, @RequestBody FilterDto filter) {
		CommonResponse res = new CommonResponse();
		res.setStatus(HttpStatus.OK.value());
		res.setMessage("Successfully fetched subCategory Data..");
		if (size != null & pageNo != null) {
			Page<SubCategory> pages = subCategoryService.findAll(size, pageNo, filter);
			res.setData(subCategoryService.domainToDto(pages));
		} else {
			res.setData(subCategoryService.domainToDto(subCategoryService.findAll()));
		}
		return new ResponseEntity<CommonResponse>(res, HttpStatus.OK);

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<CommonResponse> findOne(@PathVariable("id") final Long id) {
		CommonResponse res = new CommonResponse();
		res.setStatus(HttpStatus.OK.value());
		res.setMessage("Successfully Fetched subCategory Data..");
		res.setData(subCategoryService.domainToDto(subCategoryService.findOne(id)));
		return new ResponseEntity<CommonResponse>(res, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<CommonResponse> update(@RequestBody final SubCategoryRequest req) {

		SubCategory subCategory = subCategoryService.dtoToDomain(req);
		subCategory = subCategoryService.update(subCategory);

		CommonResponse res = new CommonResponse();
		res.setStatus(HttpStatus.OK.value());
		res.setData(subCategoryService.domainToDto(subCategory));
		res.setMessage("Successfully Updated subCategory...!");
		return new ResponseEntity<CommonResponse>(res, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PATCH)
	@ResponseBody
	public ResponseEntity<CommonResponse> partialUpdate(@RequestBody Map<String, Object> req) {
		CommonResponse res = new CommonResponse();
		res.setStatus(HttpStatus.OK.value());
		res.setData(subCategoryService.partialUpdate(req));
		res.setMessage("Successfully partial update subCategory...!");
		return new ResponseEntity<CommonResponse>(res, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<CommonResponse> create(@RequestBody final SubCategoryRequest req) {

		SubCategory subCategory = subCategoryService.dtoToDomain(req);
		subCategory = subCategoryService.create(subCategory);

		CommonResponse res = new CommonResponse();
		res.setStatus(HttpStatus.OK.value());
		res.setData(subCategoryService.domainToDto(subCategory));
		res.setMessage("Successfully Created subCategory...!");
		return new ResponseEntity<CommonResponse>(res, HttpStatus.OK);

	}

}
