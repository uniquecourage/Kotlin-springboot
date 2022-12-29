package com.example.springboot.controller

import com.example.springboot.model.StudentResponse
import com.example.springboot.service.StudentsService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.ArrayList

@RestController
class StudentController: StudentsService() {

    @GetMapping("/students")
    fun getAllStudents(): ResponseEntity<List<StudentResponse>> {
        val students = findAllStudents()
        println("$students")
        var responseList = ArrayList<StudentResponse>()
        for (s in students) {
            var studentObj = StudentResponse(s.id, s.name, s.number, s.city, s.registerDate)
            responseList.add(studentObj)
        }
        return ResponseEntity<List<StudentResponse>>(responseList, HttpStatus.OK)
    }
}