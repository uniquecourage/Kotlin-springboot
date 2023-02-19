package com.example.springboot.controller

import com.example.springboot.vo.ScoreForStudentResponse
import com.example.springboot.service.ScoresService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
class ScoreController: ScoresService() {

    @GetMapping("/api/scores/{number}")
    fun getScoresByNumber(@PathVariable("number") number: String): ResponseEntity<List<ScoreForStudentResponse>> {
        var scores = findScoreForStudent(number)
        println("$scores")
        return ResponseEntity<List<ScoreForStudentResponse>>(scores, HttpStatus.OK)
    }
}