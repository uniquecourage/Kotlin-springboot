package com.example.springboot.service

import com.example.springboot.model.Student
import com.example.springboot.model.Students
import com.example.springboot.sqlconfig.SqlConfig
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList

open class StudentsService: SqlConfig() {

    fun findAllStudents(): List<Student> =
        database.sequenceOf(Students).toList()
}