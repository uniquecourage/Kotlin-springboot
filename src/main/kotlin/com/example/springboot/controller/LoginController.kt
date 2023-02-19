package com.example.springboot.controller

import com.example.springboot.jwt.JwtTokenUtil
import com.example.springboot.vo.JwtResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
class LoginController: JwtTokenUtil() {

    @PostMapping("/token")
    fun login(@RequestBody user: HashMap <String, String>): ResponseEntity<JwtResponse> {
//        var token = generateToken(user)
        var token = generateTokenByClaims(user)
        return ResponseEntity<JwtResponse>(token, HttpStatus.OK)
    }
}