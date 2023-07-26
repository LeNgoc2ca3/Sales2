package com.Java6.restcontroller;

import java.util.List;

import com.Java6.repositoty.CategoryRepository;
import com.Java6.dto.CategoryDto;
import com.Java6.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("hasAuthority('DIRE')")
@RequestMapping(value ="/rest/category")
public class CategoryRestcontroller {
    
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository; 

    @GetMapping()
    public ResponseEntity<List<CategoryDto>> getCategory(
        @RequestParam(defaultValue="0") Integer pageIndex,
        @RequestParam(defaultValue="5") Integer pageSize,
        @RequestParam(defaultValue="id") String sortBy,
        @RequestParam(defaultValue="ASC") String sortDirection
    ){
        List<CategoryDto> list = categoryService.findAll(sortDirection, sortBy, pageIndex, pageSize);
//        System.out.println(categoryService.findAll(sortDirection, sortBy, pageIndex, pageSize));
        if(list.isEmpty())
        return ResponseEntity.noContent().build();
        return ResponseEntity.ok(list);
    }
}
