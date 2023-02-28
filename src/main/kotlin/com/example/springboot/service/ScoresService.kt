package com.example.springboot.service

import com.example.springboot.vo.ScoreForStudentResponse

interface ScoresService {
    fun findScoreForStudent(studentNumber: String): List<ScoreForStudentResponse>
}