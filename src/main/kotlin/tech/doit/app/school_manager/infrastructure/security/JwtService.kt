package tech.doit.app.school_manager.infrastructure.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm.HS256
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import tech.doit.app.school_manager.domain.database.entities.User
import java.security.Key
import java.time.Instant.now
import java.time.temporal.ChronoUnit.DAYS
import java.util.*

@Service
class JwtService {

    @Value("\${jwt.secret}")
    private lateinit var secret: String

    fun extractUsername(token: String): String =
        extractClaim(token, Claims::getSubject)

    fun generateToken(claims: Map<String, Any>, user: User): String =
        Jwts.builder()
            .setClaims(claims)
            .setSubject(user.username)
            .setIssuedAt(now().let(Date::from))
            .setExpiration(now().plus(1, DAYS).let(Date::from))
            .signWith(getSignInKey(), HS256)
            .compact()

    fun generateToken(user: User): String =
        generateToken(emptyMap(), user)

    fun isTokenValid(token: String, user: User): Boolean =
        extractUsername(token)
            .let(user.username::equals) && isTokenExpired(token).not()

    private fun isTokenExpired(token: String): Boolean =
        extractExpiration(token).before(Date())

    private fun extractExpiration(token: String): Date =
        extractClaim(token, Claims::getExpiration)

    private fun <T> extractClaim(token: String, fn: (Claims) -> T): T =
        fn(extractAllClaims(token))

    private fun extractAllClaims(token: String): Claims =
        Jwts.parserBuilder()
            .setSigningKey(getSignInKey())
            .build()
            .parseClaimsJws(token)
            .body

    private fun getSignInKey(): Key =
        Decoders.BASE64
            .decode(secret)
            .let(Keys::hmacShaKeyFor)
}