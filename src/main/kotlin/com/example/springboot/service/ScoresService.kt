package com.example.springboot.service

import com.example.springboot.model.ScoreForStudentResponse
import com.example.springboot.model.Scores
import com.example.springboot.model.Students
import com.example.springboot.sqlconfig.SqlConfig
import org.ktorm.dsl.*

open class ScoresService: SqlConfig() {

    fun findScoreForStudent(studentNumber: String): List<ScoreForStudentResponse> {
        val resultList = ArrayList<ScoreForStudentResponse>()
        database
            .from(Students)
            .innerJoin(Scores, on = Students.number eq Scores.number)
            .select(Students.name, Students.number, Scores.subject, Scores.score)
            .where(Students.number eq studentNumber)
            .forEach {row ->
                resultList.add(ScoreForStudentResponse(
                    row.getString(1),
                    row.getString(2),
                    row.getString(3),
                    row.getInt(4)
                ))
            }
        return resultList
    }
}