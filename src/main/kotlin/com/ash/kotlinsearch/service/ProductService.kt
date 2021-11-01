package com.ash.kotlinsearch.service

import com.ash.kotlinsearch.controllers.NotFoundException
import com.ash.kotlinsearch.dto.PaginatedResponse
import com.ash.kotlinsearch.models.Product
import com.ash.kotlinsearch.params.ProductListParam
import com.ash.kotlinsearch.repositories.ProductRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductService (private val productRepository: ProductRepository) {

    fun findAll(): List<Product>{
        return this.productRepository.findAll()
    }

    fun findById(string: String): Optional<Product>{
        return this.productRepository.findById(string)
    }

    fun save(product: Product) : Product {
        return this.productRepository.save(product)
    }

    fun update(string: String, product: Product) : Product{
        val oldProduct = this.productRepository.findById(string)
            .orElseThrow{ throw NotFoundException("Product not found")}
        oldProduct.title = product.title
        oldProduct.description = product.description
        oldProduct.image = product.image
        oldProduct.price = product.price
        return this.productRepository.save(oldProduct)
    }

    fun delete(string: String) {
        this.productRepository.deleteById(string)
    }

    fun findWithFilter(productListParam: ProductListParam): PaginatedResponse{
        var direction = Sort.unsorted()
        if(productListParam.sort == "asc") {
            direction = Sort.by(Sort.Direction.ASC, "price")
        } else if (productListParam.sort == "desc"){
            direction = Sort.by(Sort.Direction.DESC, "price")
        }
        val total = productRepository.countSearch(productListParam.productFilter)
            return PaginatedResponse(
                data = this.productRepository.search(productListParam.productFilter,
                    PageRequest.of(productListParam.page - 1,
                        productListParam.perPage, direction)),
                total,
                productListParam.page,
                last_page = (total/productListParam.perPage) + 1
            )
    }

}