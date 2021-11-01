package com.ash.kotlinsearch.repositories

import com.ash.kotlinsearch.models.Product
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository: MongoRepository<Product, String> {

    @Query("{'title': {\$regex:?0,\$options: 'i'}}")
    fun search(s: String, pageable: Pageable): List<Product>

    @Query("{'title': {\$regex:?0,\$options: 'i'}}", count = true)
    fun countSearch(s: String): Int
}