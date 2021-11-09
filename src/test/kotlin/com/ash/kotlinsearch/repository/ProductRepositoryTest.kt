package com.ash.kotlinsearch.repository

import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

//@SpringBootTest
@Testcontainers
@DataMongoTest
class ProductRepositoryTest {

companion object {

 @JvmStatic
 @Container
 private val mongoDBContainer = CommonMongoDBContainer().withExposedPorts(27017)


 @JvmStatic
  @DynamicPropertySource
 fun properties(registry: DynamicPropertyRegistry) {
  registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl)
  }
 }


 @Test
 fun `shouldReturnListOfProducts`() {

//  mongoDBContainer.start()
//  this.productRepository.deleteAll()
//  this.productRepository.save( Product())
//  this.productRepository.save( Product())
//  this.productRepository.save( Product())
//
//  val customers = productRepository.findAll()
//
//  assertEquals(3, customers.size)
 }


}

