package com.example.springboot.model

import kotlinx.serialization.Serializable

@Serializable
data class ScoreForStudentResponse(
    var name: String?,
    var number: String?,
    var subject: String?,
    var score: Int
)
