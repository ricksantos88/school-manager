package tech.doit.app.school_manager.infrastructure.security

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.lang.NonNull
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import tech.doit.app.school_manager.domain.database.entities.User
import tech.doit.app.school_manager.domain.service.impl.UserDetailsServiceImpl

@Component
class JwtAuthenticationFilter(
    private val jwtService: JwtService,
    private val userDetailService: UserDetailsServiceImpl
): OncePerRequestFilter() {
    override fun doFilterInternal(
        @NonNull request: HttpServletRequest,
        @NonNull response: HttpServletResponse,
        @NonNull filterChain: FilterChain
    ) {
        val authHeader = request.getHeader("Authorization")

        if(isValidaHeader(authHeader)) {
            filterChain.doFilter(request, response)
            return
        }

        val jwt = authHeader.substring(7)
        val userEmail = jwtService.extractUsername(jwt)

        if(SecurityContextHolder.getContext().authentication == null) {
            val user = userDetailService.loadUserByUsername(userEmail) as User
            if (jwtService.isTokenValid(jwt, user)) {
                UsernamePasswordAuthenticationToken(
                    user,
                    null,
                    user.authorities
                ).also {
                    it.details = WebAuthenticationDetailsSource().buildDetails(request)
                }
                    .also(
                        SecurityContextHolder.getContext()::setAuthentication
                    )
            }
        }

        filterChain.doFilter(request, response)
    }

    private fun isValidaHeader(authorization: String?) =
        authorization?.startsWith(START_TOKEN_PART) != true

    companion object {
        private const val START_TOKEN_PART = "Bearer "
    }
}