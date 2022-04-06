package com.gaur.pagination.model

data class FakeResponse(
    val `data`: List<Data>,
    val totalPages: Int,
    val totalPassengers: Int
)