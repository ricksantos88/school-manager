package tech.doit.app.school_manager.infrastructure.security.utils

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.context.annotation.Configuration
import java.util.*

@Configuration
class JwtUtil(private val secret: String) {

    companion object {
        const val TOKEN_PREFIX = "Bearer "
        const val HEADER_STRING = "Authorization"
        const val EXPIRATION_TIME: Long = 86400000 // 24 hours
    }

    fun generateToken(username: String): String {
        val key = Keys.hmacShaKeyFor(secret.toByteArray())
        val expirationDate = Date(System.currentTimeMillis() + EXPIRATION_TIME)

        return Jwts.builder()
            .setSubject(username)
            .setExpiration(expirationDate)
            .signWith(key, SignatureAlgorithm.HS512)
            .compact()
    }

    fun validateToken(token: String): Boolean {
        return try {
            val key = Keys.hmacShaKeyFor(secret.toByteArray())
            val claims: Claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .body
            !claims.expiration.before(Date())
        } catch (e: Exception) {
            false
        }
    }

    fun getUsernameFromToken(token: String): String? {
        return try {
            val claims: Claims = Jwts.parserBuilder()
                .setSigningKey(secret.toByteArray())
                .build()
                .parseClaimsJws(token)
                .body
            claims.subject
        } catch (e: Exception) {
            null
        }
    }
}