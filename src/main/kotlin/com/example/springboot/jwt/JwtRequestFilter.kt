package com.example.springboot.jwt

import io.jsonwebtoken.ExpiredJwtException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.util.ArrayList


@Component
class JwtRequestFilter: OncePerRequestFilter() {

    @Autowired
    private lateinit var jwtTokenUtil: JwtTokenUtil

    private val prefix = "Bearer "

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        var username: Any? = null
        var jwtToken: String? = null

        val requestTokenHeader = request.getHeader("Authorization")
        if (requestTokenHeader != null && requestTokenHeader.startsWith(prefix)) {
            jwtToken = requestTokenHeader.substring(prefix.length)
            try {
                username = jwtTokenUtil.getUsernameFromToken(jwtToken)
            } catch (e: IllegalArgumentException) {
                println("Unable to get JWT Token")
            } catch (e: ExpiredJwtException) {
                println("JWT Token has expired")
            }
        } else {
            println("No JWT Token")
        }

//        implement authentication
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            userDetails needs to assign User(username, password, ArrayList<>())
            val userDetails = User("admin", "admin", ArrayList<SimpleGrantedAuthority>())
            if (jwtTokenUtil.isValidToken(jwtToken, userDetails)) {
                println("jwt token is valid")
                val usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities())
                usernamePasswordAuthenticationToken
                    .setDetails(WebAuthenticationDetailsSource().buildDetails(request))
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken)
            } else {
                println("jwt token is invalid")
            }
        }

        println("filterChain doFilter : $username")
        filterChain.doFilter(request, response)
    }
}