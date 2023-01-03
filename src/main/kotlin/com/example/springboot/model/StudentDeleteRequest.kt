package com.example.springboot.model

import kotlinx.serialization.Serializable

@Serializable
data class StudentDeleteRequest(
//    Only one parameter can not be serializable with annotation
    var number: String,
    var name: String
)
