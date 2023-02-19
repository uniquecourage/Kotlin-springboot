package com.example.springboot.controller

import com.example.springboot.jwt.JwtTokenUtil
import jakarta.security.auth.message.AuthException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController


@RestController
@CrossOrigin
class HelloController {

    @Autowired
    private lateinit var jwtTokenUtil: JwtTokenUtil

    @GetMapping("/test/hello")
    fun hello(): String {
        return "hello springboot with kotlin"
    }

    @GetMapping("/api/auth")
    fun testAuth(): ResponseEntity<Any> {
        return ResponseEntity<Any>("success", HttpStatus.OK)
    }

    @GetMapping("/api/demo")
    fun testDemo(): ResponseEntity<Any> {
        return ResponseEntity<Any>("demo api", HttpStatus.OK)
    }
}