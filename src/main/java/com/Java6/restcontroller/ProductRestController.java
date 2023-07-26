package com.Java6.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.Java6.repositoty.ProductRepository;
import com.Java6.util.ObjectMapperUtils;
import com.Java6.dto.ProductDto;
import com.Java6.service.ProductService;

@CrossOrigin("*")
@RestController
//@PreAuthorize("hasAuthority('DIRE')")
@RequestMapping(value = "/rest/products")
public class ProductRestController {

	@Autowired
	ProductService productService;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	private ObjectMapperUtils objectMapperUtils;
	// @GetMapping(value = "{id}")
	// public Product getOne(@PathVariable("id") String id) {
	// return productService.findById(Integer.parseInt(id));
	// }

	@GetMapping(value = "")
	public ResponseEntity<List<ProductDto>> getCategory(@RequestParam(defaultValue = "0") Integer pageIndex,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "ASC") String sortDirection, @RequestParam(defaultValue = "") String name) {
		HttpHeaders headers = new HttpHeaders();
		Integer pageTotal = (productRepository.getTotalProductCount() % pageSize) == 0
				? ((productRepository.getTotalProductCount() / pageSize) - 1)
				: ((productRepository.getTotalProductCount() / pageSize));
		List<ProductDto> list = productService.findAll(name, sortDirection, sortBy, pageIndex, pageSize);
		headers.add("boolean", pageTotal.toString());
		if (list.isEmpty())
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok().headers(headers).body(list);
	}

//	@PreAuthorize("hasAuthority('DIRE')")
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<ProductDto> getOne(@PathVariable("id") Integer id) {
		ProductDto productDto = productService.findById(id);
//		System.out.println(productDto);
		return ResponseEntity.ok(productDto);
	}

//	@PreAuthorize("hasAuthority('DIRE')")
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<ProductDto> deleteOne(@PathVariable("id") Integer id) {
		if (!productRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		if (productService.delete(id)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}

//	@PreAuthorize("hasAuthority('DIRE')")
	@PostMapping(value = "/create")
	public ResponseEntity<ProductDto> create(@RequestBody ProductDto productDto) {
		if (productDto.getId() != null) {
			return ResponseEntity.ok(productService.update(productDto));
		}
		System.out.println(productDto);
		return ResponseEntity.ok(productService.create(productDto));
	}

//	@PreAuthorize("hasAuthority('DIRE')")
	@PutMapping(value = "/update/{id}")
	public ResponseEntity<ProductDto> update(@RequestBody ProductDto productDto, @PathVariable("id") Integer id,
			@RequestParam(value = "categoryId") String categoryId) {
		if (id != null)
			return ResponseEntity.ok(productService.update(productDto, categoryId));
		return ResponseEntity.badRequest().build();
	}

}
