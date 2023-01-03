package com.example.springboot.service

import com.example.springboot.model.*
import com.example.springboot.sqlconfig.SqlConfig
import org.ktorm.dsl.eq
import org.ktorm.entity.add
import org.ktorm.entity.find
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList

open class StudentsService: SqlConfig() {

    fun findAllStudents(): List<Student> =
        database.sequenceOf(Students).toList()

    fun findStudentByNumber(studentNumber: String): Student? =
    database.sequenceOf(Students)
    .find { student -> student.number eq studentNumber }

    fun addStudent(studentRequest: StudentRequest): Boolean {
        val newStudent = Student {
            name = studentRequest.name
            number = studentRequest.number
            city = studentRequest.city
            registerDate = studentRequest.registerDate
        }

        val affectedRecordsNumber =
            database.sequenceOf(Students)
                .add(newStudent)

        return affectedRecordsNumber == 1
    }

    fun updateStudent(studentUpdateRequest: StudentUpdateRequest): Boolean {
        val foundStudent = findStudentByNumber(studentUpdateRequest.number)
        foundStudent?.number = studentUpdateRequest.number
        foundStudent?.city = studentUpdateRequest.city

        val affectedRecordsNumber = foundStudent?.flushChanges()
        return affectedRecordsNumber == 1
    }

    fun deleteStudent(studentDeleteRequest: StudentDeleteRequest): Boolean {
        val foundStudent = findStudentByNumber(studentDeleteRequest.number)

        val affectedRecordsNumber = foundStudent?.delete()
        return affectedRecordsNumber == 1
    }
}