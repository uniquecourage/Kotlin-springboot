package com.example.springboot.vo

import kotlinx.serialization.Serializable

@Serializable
data class JwtResponse(
    var token: String
)
