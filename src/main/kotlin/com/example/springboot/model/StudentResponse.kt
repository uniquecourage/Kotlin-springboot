package com.example.springboot.model

import kotlinx.serialization.Serializable
import java.time.LocalDate


@Serializable
data class StudentResponse(
    var id: Long,
    var name: String,
    var number: String,
    var city: String,
    var registerDate: LocalDate
)
