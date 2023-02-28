package com.example.springboot.sqlconfig

import org.ktorm.database.Database

interface SqlConfig {
    fun sqlQuery(): Database
}