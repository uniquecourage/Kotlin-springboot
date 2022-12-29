package com.example.springboot.sqlconfig

import org.ktorm.database.Database

open class SqlConfig {
    val database = Database.connect(
        url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=practice",
        driver = "org.postgresql.Driver",
        user = "postgres",
        password = "123456"
    )
}