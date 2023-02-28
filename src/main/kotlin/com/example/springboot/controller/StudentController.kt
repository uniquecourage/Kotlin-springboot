package com.example.springboot.controller

import com.example.springboot.service.StudentsService
import com.example.springboot.vo.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.ArrayList

@RestController
@CrossOrigin
class StudentController {

    @Autowired
    private lateinit var studentsService: StudentsService

    @GetMapping("/api/students")
    fun getAllStudents(): ResponseEntity<List<StudentResponse>> {
        val students = studentsService.findAllStudents()
        println("$students")
        var responseList = ArrayList<StudentResponse>()
        for (s in students) {
            var studentObj = StudentResponse(s.id, s.name, s.number, s.city, s.registerDate)
            responseList.add(studentObj)
        }
        return ResponseEntity<List<StudentResponse>>(responseList, HttpStatus.OK)
    }

    @GetMapping("/api/students/{number}")
    fun getStudentByNumber(@PathVariable("number") number: String): ResponseEntity<StudentResponse> {
        val student = studentsService.findStudentByNumber(number)
        println("$student")
        var response = student?.let { StudentResponse(it.id, student.name, student.number, student.city, student.registerDate) }
        return ResponseEntity<StudentResponse>(response, HttpStatus.OK)
    }

    @PostMapping("/api/students/add")
    fun createStudent(@RequestBody requestVO: StudentRequest): ResponseEntity<Response> {
        println("$requestVO")
        val success = studentsService.addStudent(studentRequest = requestVO)
        var resonse = Response(success)
        if (success) {
            return ResponseEntity<Response>(resonse, HttpStatus.OK)
        } else {
            return ResponseEntity<Response>(resonse, HttpStatus.BAD_REQUEST)
        }
    }

    @PostMapping("/api/students/update")
    fun updateStudentByNumber(@RequestBody requestVO: StudentUpdateRequest): ResponseEntity<Response> {
        println("$requestVO")
        val success = studentsService.updateStudent(requestVO)
        var resonse = Response(success)
        if (success) {
            return ResponseEntity<Response>(resonse, HttpStatus.OK)
        } else {
            return ResponseEntity<Response>(resonse, HttpStatus.BAD_REQUEST)
        }
    }

    @PostMapping("/api/students/delete")
    fun deleteStudentByNumber(@RequestBody requestVO: StudentDeleteRequest): ResponseEntity<Response> {
        println("$requestVO")
        val success = studentsService.deleteStudent(requestVO)
        var resonse = Response(success)
        if (success) {
            return ResponseEntity<Response>(resonse, HttpStatus.OK)
        } else {
            return ResponseEntity<Response>(resonse, HttpStatus.BAD_REQUEST)
        }
    }
}