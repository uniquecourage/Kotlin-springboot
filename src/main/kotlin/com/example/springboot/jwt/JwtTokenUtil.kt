package com.example.springboot.jwt

import com.example.springboot.vo.JwtResponse
import io.jsonwebtoken.*
import jakarta.security.auth.message.AuthException
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

open class JwtTokenUtil {

    private val secret = "MY_SECRET"
    private val expiration = 9000000
    private val prefix = "Bearer "

//    fun generateToken(userDetails: HashMap<String, String> ): JwtResponse {
//        var userName = userDetails.get("username")
//        println("username is $userName")
//        val token = Jwts.builder().setSubject(userName).setExpiration(Date(System.currentTimeMillis() + expiration))
//            .signWith(SignatureAlgorithm.HS512, secret).compact()
//        var jwtResponse = JwtResponse(token)
//        return jwtResponse
//    }

    fun generateTokenByClaims(userDetails: HashMap<String, String> ): JwtResponse {
        var userName = userDetails.get("username")
        println("username is $userName")
        var password = userDetails.get("password")
        println("password is $password")
//        val mapTest = mapOf<String, String>()
        val claims = mapOf("username" to userName, "password" to password)
        val token = Jwts.builder().setClaims(claims).setExpiration(Date(System.currentTimeMillis() + expiration))
            .signWith(SignatureAlgorithm.HS512, secret).compact()
        val newToken = StringBuilder()
        newToken.append(prefix)
            .append(token)
        var jwtResponse = JwtResponse(newToken.toString())
        return jwtResponse
    }

//    private fun getClaims(token: String?) =
//        Jwts.parser().setSigningKey(secret).parseClaimsJws(token).body

    private fun getClaims(token: String?): Claims {
        val claims: Any?
        try {
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).body
        } catch (e: Exception) {
            throw AuthException("JWT token is invalid")
        }
        return claims
    }

//    fun getEmail(token: String): String = getClaims(token).subject

    fun isTokenExpired(token: String?): Boolean {
        val claims = getClaims(token)
        val expirationDate = claims.expiration
        val now = Date(System.currentTimeMillis())
        return now.before(expirationDate)
    }

    fun isValidToken(token: String?, userDetails: UserDetails): Boolean {
        val username = getUsernameFromToken(token)
        return (username.toString().equals(userDetails.username)) && isTokenExpired(token)
    }

    fun getUsernameFromToken(token: String?): Any? {
        val claims = getClaims(token)
        val username = claims.get("username")
        return username
    }

//    fun validateToken(token: String) {
////        if (!token.startsWith(prefix)) {
////            throw AuthException("Invalid JWT token.")
////        }
//        try {
//            val realToken = if (token.startsWith(prefix)) token.substring(prefix.length) else token
//            Jwts.parser()
//                .setSigningKey(secret)
//                .parseClaimsJws(realToken)
//        } catch (e: SignatureException) {
//            throw AuthException("Invalid JWT signature.")
//        } catch (e: MalformedJwtException) {
//            throw AuthException("Invalid JWT token.")
//        } catch (e: ExpiredJwtException) {
//            throw AuthException("Expired JWT token.")
//        } catch (e: UnsupportedJwtException) {
//            throw AuthException("Unsupported JWT token.")
//        } catch (e: IllegalArgumentException) {
//            throw AuthException("JWT token compact of handler are invalid.")
//        }
//    }

}