package com.ash.kotlinsearch.controllers

import com.ash.kotlinsearch.dto.PaginatedResponse
import com.ash.kotlinsearch.models.Product
import com.ash.kotlinsearch.params.ProductListParam
import com.ash.kotlinsearch.service.ProductService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1")
@Api(description = "Test swagger")
class ProductController(private val productService: ProductService) {

    @GetMapping("/products/page")
    @ApiOperation("find all products")
        fun page(): ResponseEntity<List<Product>>{
            return ResponseEntity.ok(this.productService.findAll())
        }

    @GetMapping("/products/backend")
    @ApiOperation("search products")
    fun search(productListParam: ProductListParam
    ): ResponseEntity<PaginatedResponse>{
        return ResponseEntity.ok(productService.findWithFilter(productListParam))
    }

    @GetMapping("/products/{id}")
    fun getById(@PathVariable id: String): ResponseEntity<Product>{
        return ResponseEntity.ok(productService.findById(id)
            .orElseThrow { throw NotFoundException("Product not found") } )
    }

    @PutMapping("/products/{id}")
    fun update(
        @PathVariable id: String,
        @RequestBody product: Product
    ) : ResponseEntity<Product>{
        return ResponseEntity.ok(this.productService.update(id, product))
    }

    @PostMapping("/products")
    fun create(@RequestBody product: Product): ResponseEntity<Product>{
        return ResponseEntity.ok(this.productService.save(product))
    }

    @DeleteMapping("/products/{id}")
    fun delete(@PathVariable id : String): ResponseEntity<String>{
        this.productService.delete(id)
        return ResponseEntity.ok(id)
    }
}