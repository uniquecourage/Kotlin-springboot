package com.example.springboot.vo

import kotlinx.serialization.Serializable

@Serializable
data class StudentUpdateRequest(
    var number: String,
    var city: String
)
