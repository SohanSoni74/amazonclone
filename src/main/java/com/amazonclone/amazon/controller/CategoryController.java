package com.amazonclone.amazon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonclone.amazon.common.ApiResponse;
import com.amazonclone.amazon.entities.Category;
import com.amazonclone.amazon.service.CategoryService;

@RestController
@RequestMapping("/category")
//@CrossOrigin(origins = "*" )
public class CategoryController {

	@Autowired
	public CategoryService categoryService;
	
	@PostMapping("/create")
	public ResponseEntity<ApiResponse> createCategory(@RequestBody Category category) {
		categoryService.createCategory(category);
		return new ResponseEntity<>(new ApiResponse(true ,"New Category Created!!"), HttpStatus.CREATED);
	}
	
	@GetMapping("/list")
	public List<Category> listcategory() {
		return categoryService.listCategory();
	}
	
	@PostMapping("/update/{categoryId}")
	public ResponseEntity<ApiResponse> updateCategory(@PathVariable ("categoryId") int categoryId, @RequestBody Category category) {
		System.out.println("category id " + categoryId);
		if(categoryService.findById(categoryId)) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false ,"Category Doesn't Exist!!"), HttpStatus.NOT_FOUND);
		}
		categoryService.editCategory(categoryId, category);
		return new ResponseEntity<ApiResponse>(new ApiResponse(true ,"Category Updated!!"), HttpStatus.OK);
	}
}

