package com.example.springboot.model

import org.ktorm.entity.Entity
import org.ktorm.schema.*

//define db table scores
interface Score : Entity<Score> {
    companion object : Entity.Factory<Score>()

    val id: Long
    var number: String
    var subject: String
    var score: Int
}

object Scores : Table<Score>("scores") {
    val id = long("id").primaryKey().bindTo(Score::id)
    val number = varchar("number").bindTo(Score::number)
    val subject = varchar("subject").bindTo(Score::subject)
    val score = int("score").bindTo(Score::score)
}