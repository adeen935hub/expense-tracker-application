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
import com.expensetracker.common.dto.request.CategoryRequest;
import com.expensetracker.common.web.CommonResponse;
import com.expensetracker.domain.Category;
import com.expensetracker.service.ICategoryService;

@RestController
@RequestMapping(value = "category")
public class CategoryController {
	@Autowired
	private ICategoryService categoryService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<CommonResponse> findAll(@RequestParam(required = false) Integer size,
			@RequestParam(required = false) Integer pageNo) {
		CommonResponse res = new CommonResponse();
		res.setStatus(HttpStatus.OK.value());
		res.setMessage("Successfully fetched category Data..");
		if (size != null & pageNo != null) {
			FilterDto filter = new FilterDto();
			Page<Category> pages = categoryService.findAll(size, pageNo, filter);
			res.setData(categoryService.domainToDto(pages));
		} else {
			res.setData(categoryService.domainToDto(categoryService.findAll()));
		}
		return new ResponseEntity<CommonResponse>(res, HttpStatus.OK);

	}

	@RequestMapping(value = "/filter", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<CommonResponse> findAll(@RequestParam(required = false) Integer size,
			@RequestParam(required = false) Integer pageNo, @RequestBody FilterDto filter) {
		CommonResponse res = new CommonResponse();
		res.setStatus(HttpStatus.OK.value());
		res.setMessage("Successfully fetched category Data..");
		if (size != null & pageNo != null) {
			Page<Category> pages = categoryService.findAll(size, pageNo, filter);
			res.setData(categoryService.domainToDto(pages));
		} else {
			res.setData(categoryService.domainToDto(categoryService.findAll()));
		}
		return new ResponseEntity<CommonResponse>(res, HttpStatus.OK);

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<CommonResponse> findOne(@PathVariable("id") final Long id) {
		CommonResponse res = new CommonResponse();
		res.setStatus(HttpStatus.OK.value());
		res.setMessage("Successfully Fetched category Data..");
		res.setData(categoryService.domainToDto(categoryService.findOne(id)));
		return new ResponseEntity<CommonResponse>(res, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<CommonResponse> update(@RequestBody final CategoryRequest req) {

		Category category = categoryService.dtoToDomain(req);
		category = categoryService.update(category);

		CommonResponse res = new CommonResponse();
		res.setStatus(HttpStatus.OK.value());
		res.setData(categoryService.domainToDto(category));
		res.setMessage("Successfully Updated category...!");
		return new ResponseEntity<CommonResponse>(res, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PATCH)
	@ResponseBody
	public ResponseEntity<CommonResponse> partialUpdate(@RequestBody Map<String, Object> req) {
		CommonResponse res = new CommonResponse();
		res.setStatus(HttpStatus.OK.value());
		res.setData(categoryService.partialUpdate(req));
		res.setMessage("Successfully partial update category...!");
		return new ResponseEntity<CommonResponse>(res, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<CommonResponse> create(@RequestBody final CategoryRequest req) {

		Category category = categoryService.dtoToDomain(req);
		category = categoryService.create(category);

		CommonResponse res = new CommonResponse();
		res.setStatus(HttpStatus.OK.value());
		res.setData(categoryService.domainToDto(category));
		res.setMessage("Successfully Created category...!");
		return new ResponseEntity<CommonResponse>(res, HttpStatus.OK);

	}

}
