package com.example.springboot.vo

import kotlinx.serialization.Serializable

@Serializable
data class StudentDeleteRequest(
//    Only one parameter can not be serializable with annotation
    var number: String,
    var name: String
)
