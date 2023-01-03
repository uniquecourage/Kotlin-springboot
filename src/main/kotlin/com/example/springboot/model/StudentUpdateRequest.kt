package com.example.springboot.model

import kotlinx.serialization.Serializable

@Serializable
data class StudentUpdateRequest(
    var number: String,
    var city: String
)
