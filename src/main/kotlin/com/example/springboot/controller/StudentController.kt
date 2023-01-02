package com.example.springboot.controller

import com.example.springboot.model.Response
import com.example.springboot.model.StudentRequest
import com.example.springboot.model.StudentResponse
import com.example.springboot.service.StudentsService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
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

    @GetMapping("/students/{number}")
    fun getStudentByNumber(@PathVariable("number") number: String): ResponseEntity<StudentResponse> {
        val student = findStudentByNumber(number)
        println("$student")
        var response = student?.let { StudentResponse(it.id, student.name, student.number, student.city, student.registerDate) }
        return ResponseEntity<StudentResponse>(response, HttpStatus.OK)
    }

    @PostMapping("/students/add")
    fun createStudent(@RequestBody requestVO: StudentRequest): ResponseEntity<Response> {
        println("$requestVO")
        val success = addStudent(studentRequest = requestVO)
        var resonse = Response(success)
        if (success) {
            return ResponseEntity<Response>(resonse, HttpStatus.OK)
        } else {
            return ResponseEntity<Response>(resonse, HttpStatus.BAD_REQUEST)
        }
    }
}