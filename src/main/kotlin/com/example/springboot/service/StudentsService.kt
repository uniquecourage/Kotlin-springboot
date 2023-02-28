package com.example.springboot.service

import com.example.springboot.model.*
import com.example.springboot.vo.StudentDeleteRequest
import com.example.springboot.vo.StudentRequest
import com.example.springboot.vo.StudentUpdateRequest

interface StudentsService {

    fun findAllStudents(): List<Student>

    fun findStudentByNumber(studentNumber: String): Student?

    fun addStudent(studentRequest: StudentRequest): Boolean

    fun updateStudent(studentUpdateRequest: StudentUpdateRequest): Boolean

    fun deleteStudent(studentDeleteRequest: StudentDeleteRequest): Boolean
}