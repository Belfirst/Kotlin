package com.ash.kotlinsearch.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class Product {

    @Id
    lateinit var id:String
    var title = ""
    var description = ""
    var image = ""
    var price = 0
}