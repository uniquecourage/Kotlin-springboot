package com.example.springboot.controller

import com.example.springboot.jwt.JwtTokenUtil
import jakarta.security.auth.message.AuthException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController


@RestController
class HelloController {

    @Autowired
    private lateinit var jwtTokenUtil: JwtTokenUtil

    @GetMapping("/test/hello")
    fun hello(): String {
        return "hello springboot with kotlin"
    }

    @GetMapping("/test/auth")
    fun testAuth(@RequestHeader("Authorization") au:String): ResponseEntity<Any> {
        try {
            jwtTokenUtil.validateToken(au)
        } catch (e: AuthException) {
            return ResponseEntity<Any>(e.message, HttpStatus.FORBIDDEN)
        }
        return ResponseEntity<Any>("success", HttpStatus.OK)
    }
}