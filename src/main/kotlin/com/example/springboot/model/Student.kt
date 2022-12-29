package com.example.springboot.model

import org.ktorm.entity.Entity
import org.ktorm.schema.*
import java.time.LocalDate

interface Student : Entity<Student> {
    companion object : Entity.Factory<Student>()

    val id: Long
    var name: String
    var number: String
    var city: String
    var registerDate: LocalDate
}

object Students : Table<Student>("students") {
    val id = long("id").primaryKey().bindTo(Student::id)
    val name = varchar("name").bindTo(Student::name)
    val number = varchar("number").bindTo(Student::number)
    val city = varchar("city").bindTo(Student::city)
    val registerDate = date("register_date").bindTo(Student::registerDate)
}