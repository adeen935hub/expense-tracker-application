package com.expensetracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.expensetracker.common.dto.FilterDto;
import com.expensetracker.common.web.CommonResponse;
import com.expensetracker.domain.User;
import com.expensetracker.service.IUserService;

@RestController
@RequestMapping(value = "user")
public class UserController {
	@Autowired
	private IUserService userService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<CommonResponse> findAll(@RequestParam(required = false) Integer size,
			@RequestParam(required = false) Integer pageNo) {
		CommonResponse res = new CommonResponse();
		res.setStatus(HttpStatus.OK.value());
		res.setMessage("Successfully fetched user Data..");
		FilterDto filter = new FilterDto();
		filter.getFilter().put("active", true);
		if (size != null & pageNo != null) {

			Page<User> pages = userService.findAll(size, pageNo, filter);
			res.setData(userService.domainToDto(pages));
		} else {
			res.setData(userService.domainToDto(userService.findAll(filter)));
		}
		return new ResponseEntity<CommonResponse>(res, HttpStatus.OK);

	}

//	@RequestMapping(value = "/filter", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseEntity<CommonResponse> findAll(@RequestParam(required = false) Integer size,
//			@RequestParam(required = false) Integer pageNo, @RequestBody FilterDto filter) {
//		CommonResponse res = new CommonResponse();
//		res.setStatus(HttpStatus.OK.value());
//		res.setMessage("Successfully fetched user Data..");
//		if (size != null & pageNo != null) {
//			Page<User> companiesPage = userService.findAll(size, pageNo, filter);
//			res.setData(userService.domainToDto(companiesPage));
//		} else {
//			res.setData(userService.domainToDto(userService.findAll()));
//		}
//		return new ResponseEntity<CommonResponse>(res, HttpStatus.OK);
//
//	}
//
//	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
//	@ResponseBody
//	public ResponseEntity<CommonResponse> findOne(@PathVariable("id") final Long id) {
//		CommonResponse res = new CommonResponse();
//		res.setStatus(HttpStatus.OK.value());
//		res.setMessage("Successfully Fetched user Data..");
//		res.setData(userService.domainToDto(userService.findOne(id)));
//		return new ResponseEntity<CommonResponse>(res, HttpStatus.OK);
//
//	}
//
//	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//	@ResponseBody
//	public ResponseEntity<CommonResponse> delete(@PathVariable("id") final Long id) {
//
//		CommonResponse res = new CommonResponse();
//		res.setStatus(HttpStatus.OK.value());
//		// userService.delete(id);
//		res.setData(userService.logicalDelete(id));
//		res.setMessage("Successfully deleted user ..!");
//
//		return new ResponseEntity<CommonResponse>(res, HttpStatus.OK);
//
//	}
//
//	@RequestMapping(method = RequestMethod.PUT)
//	@ResponseBody
//	public ResponseEntity<CommonResponse> update(@RequestBody final UserRequestDto req) {
//
//		User user = userService.dtoToDomain(req);
//		user = userService.update(user);
//
//		CommonResponse res = new CommonResponse();
//		res.setStatus(HttpStatus.OK.value());
//		res.setData(userService.domainToDto(user));
//		res.setMessage("Successfully Updated user...!");
//		return new ResponseEntity<CommonResponse>(res, HttpStatus.OK);
//	}
//
//	@RequestMapping(method = RequestMethod.PATCH)
//	@ResponseBody
//	public ResponseEntity<CommonResponse> partialUpdate(@RequestBody Map<String, Object> req) {
//		CommonResponse res = new CommonResponse();
//		res.setStatus(HttpStatus.OK.value());
//		res.setData(userService.partialUpdate(req));
//		res.setMessage("Successfully partial update user...!");
//		return new ResponseEntity<CommonResponse>(res, HttpStatus.OK);
//	}
//
//	@RequestMapping(method = RequestMethod.POST)
//	@ResponseStatus(HttpStatus.CREATED)
//	public ResponseEntity<CommonResponse> create(@RequestBody final UserRequestDto req) {
//
//		User user = userService.dtoToDomain(req);
//		user = userService.create(user);
//
//		CommonResponse res = new CommonResponse();
//		res.setStatus(HttpStatus.OK.value());
//		res.setData(userService.domainToDto(user));
//		res.setMessage("Successfully Created user...!");
//		return new ResponseEntity<CommonResponse>(res, HttpStatus.OK);
//
//	}

}
