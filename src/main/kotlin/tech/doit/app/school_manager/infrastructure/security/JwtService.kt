package tech.doit.app.school_manager.infrastructure.security

import org.springframework.security.oauth2.jwt.JwtClaimsSet
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.JwtEncoderParameters
import org.springframework.stereotype.Service
import tech.doit.app.school_manager.domain.database.entities.User
import java.time.Instant.now
import java.time.temporal.ChronoUnit.DAYS

@Service
class JwtService(
    private val jwtEncoder: JwtEncoder
) {

    fun generateToken(scopes: String, user: User): String =
        JwtClaimsSet.builder()
            .subject(user.username)
            .issuedAt(now())
            .expiresAt(now().plus(1, DAYS))
            .claim("scope", scopes)
            .build()
            .let{ JwtEncoderParameters.from(it) }
            .let { jwtEncoder.encode(it) }
            .tokenValue

    fun generateToken(user: User): String {
        val scopes = user.authorities.joinToString(separator = " ") { it.authority }
        return generateToken(scopes, user)
    }
}