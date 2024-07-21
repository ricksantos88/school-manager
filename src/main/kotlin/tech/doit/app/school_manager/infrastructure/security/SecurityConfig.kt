package tech.doit.app.school_manager.infrastructure.security

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletResponse
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.context.NullSecurityContextRepository

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun filterChain(httpSecurity: HttpSecurity): SecurityFilterChain =
        httpSecurity.authorizeHttpRequests{ httpRequests ->
            httpRequests.requestMatchers(*PERMIT_URI).permitAll()
        }
        .securityContext { context ->
            /**
             * Faz com que o spring não guarde uma sessão, ou seja, a cada nova requisição é preciso passar o header
             */
            context.securityContextRepository(NullSecurityContextRepository())
        }
        .csrf{ csrf ->
            csrf.disable()
        }
        .exceptionHandling { handler ->
            handler.authenticationEntryPoint(securityEntryPoint())
        }
        .build()


    fun securityEntryPoint() =
        AuthenticationEntryPoint { _, response, _ ->
            val message = ObjectMapper().writeValueAsString(mapOf("error" to "Unauthorized access"))
            response.status = HttpServletResponse.SC_UNAUTHORIZED
            response.contentType = MediaType.APPLICATION_JSON_VALUE
            response.outputStream.println(message)
        }

    companion object {
        private val PERMIT_URI = arrayOf(
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/v3/api-docs/**",
            "/swagger-ui/**"
        )
    }
}