package com.example.springboot.service.impl

import com.example.springboot.model.Scores
import com.example.springboot.model.Students
import com.example.springboot.service.ScoresService
import com.example.springboot.sqlconfig.SqlConfig
import com.example.springboot.vo.ScoreForStudentResponse
import org.ktorm.dsl.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ScoresServiceImpl: ScoresService {

    @Autowired
    private lateinit var sqlConfig: SqlConfig

    override fun findScoreForStudent(studentNumber: String): List<ScoreForStudentResponse> {
        val resultList = ArrayList<ScoreForStudentResponse>()
        sqlConfig.sqlQuery()
            .from(Students)
            .innerJoin(Scores, on = Students.number eq Scores.number)
            .select(Students.name, Students.number, Scores.subject, Scores.score)
            .where(Students.number eq studentNumber)
            .forEach {row ->
                resultList.add(
                    ScoreForStudentResponse(
                        row.getString(1),
                        row.getString(2),
                        row.getString(3),
                        row.getInt(4)
                    )
                )
            }
        return resultList
    }
}