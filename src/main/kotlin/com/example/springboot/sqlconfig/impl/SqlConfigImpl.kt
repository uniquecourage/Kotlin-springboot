package com.example.springboot.sqlconfig.impl

import com.example.springboot.sqlconfig.SqlConfig
import org.ktorm.database.Database
import org.springframework.stereotype.Service

@Service
class SqlConfigImpl: SqlConfig {

    override fun sqlQuery(): Database {
        val database = Database.connect(
            url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=practice",
            driver = "org.postgresql.Driver",
            user = "postgres",
            password = "123456"
        )
        return database
    }
}