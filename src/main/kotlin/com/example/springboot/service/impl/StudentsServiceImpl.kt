package com.example.springboot.service.impl

import com.example.springboot.model.Student
import com.example.springboot.model.Students
import com.example.springboot.service.StudentsService
import com.example.springboot.sqlconfig.SqlConfig
import com.example.springboot.vo.StudentDeleteRequest
import com.example.springboot.vo.StudentRequest
import com.example.springboot.vo.StudentUpdateRequest
import org.ktorm.dsl.eq
import org.ktorm.entity.add
import org.ktorm.entity.find
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class StudentsServiceImpl: StudentsService {

    @Autowired
    private lateinit var sqlConfig: SqlConfig

    override fun findAllStudents(): List<Student> =
        sqlConfig.sqlQuery().sequenceOf(Students).toList()

    override fun findStudentByNumber(studentNumber: String): Student? =
        sqlConfig.sqlQuery().sequenceOf(Students)
            .find { student -> student.number eq studentNumber }

    override fun addStudent(studentRequest: StudentRequest): Boolean {
        val newStudent = Student {
            name = studentRequest.name
            number = studentRequest.number
            city = studentRequest.city
            registerDate = studentRequest.registerDate
        }

        val affectedRecordsNumber =
            sqlConfig.sqlQuery().sequenceOf(Students)
                .add(newStudent)

        return affectedRecordsNumber == 1
    }

    override fun updateStudent(studentUpdateRequest: StudentUpdateRequest): Boolean {
        val foundStudent = findStudentByNumber(studentUpdateRequest.number)
        foundStudent?.number = studentUpdateRequest.number
        foundStudent?.city = studentUpdateRequest.city

        val affectedRecordsNumber = foundStudent?.flushChanges()
        return affectedRecordsNumber == 1
    }

    override fun deleteStudent(studentDeleteRequest: StudentDeleteRequest): Boolean {
        val foundStudent = findStudentByNumber(studentDeleteRequest.number)

        val affectedRecordsNumber = foundStudent?.delete()
        return affectedRecordsNumber == 1
    }
}