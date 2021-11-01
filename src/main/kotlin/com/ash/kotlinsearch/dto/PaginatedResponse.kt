package com.ash.kotlinsearch.dto

class PaginatedResponse (
    val data: List<Any>,
    val total: Int,
    val page: Int,
    val last_page: Int
        ){}