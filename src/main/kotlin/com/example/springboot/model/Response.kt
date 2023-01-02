package com.example.springboot.model

import kotlinx.serialization.Serializable

@Serializable
data class Response(
    var status: Boolean
)
