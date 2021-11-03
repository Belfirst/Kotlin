package com.ash.kotlinsearch.service

import com.ash.kotlinsearch.models.Product
import com.ash.kotlinsearch.repositories.ProductRepository
import org.junit.Assert
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import java.util.*

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private lateinit var productService: ProductService

    @MockBean
    private lateinit var productRepository: ProductRepository

    @Test
    fun `should save and return product`() {
        val product  = Product()
        `when`(productRepository.save(product)).thenReturn(product)
        val productCreate = productService.save(product)
        Assert.assertEquals(productCreate, product)
        Mockito.verify(productRepository, Mockito.times(1)).save(product)
    }

    @Test
    fun `should update and return product`(){
        val product = Product()
        product.id = "1"
        product.price = 10
        val product1 = Product()
        product1.id = "1"
        product1.price = 0
        `when`(productRepository.findById(product.id)).thenReturn(Optional.ofNullable(product))
        `when`(productRepository.save(product)).thenReturn(product1)
        Assert.assertNotEquals(productService.update(product.id,product), product)
        Mockito.verify(productRepository).findById(product.id)
        Mockito.verify(productRepository).save(product)
    }

    @Test
    fun `should return list of all products`(){
        val product1 = Product()
        val product2 = Product()
        val productList = listOf(product1, product2)
        `when`(productRepository.findAll()).thenReturn(productList)
        val productListTest = productService.findAll()
        Assert.assertEquals(productListTest, productListTest)
        Mockito.verify(productRepository).findAll()
    }

    @Test
    fun `should return product of that id`(){
        val product = Product()
        product.id = "1"
        `when`(productRepository.findById("1")).thenReturn(Optional.ofNullable(product))
        val productTest = productService.findById(product.id).orElse(null)
        Mockito.verify(productRepository).findById("1")
        Assert.assertEquals(productTest, product)
    }

    @Test
    fun `should delete the product`(){
        val product = Product()
        product.id = ""
        productService.delete(product.id)
        Mockito.verify(productRepository).deleteById(product.id)
    }

}