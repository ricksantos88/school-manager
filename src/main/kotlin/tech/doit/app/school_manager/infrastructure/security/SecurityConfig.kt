package tech.doit.app.school_manager.infrastructure.security

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.context.NullSecurityContextRepository
import tech.doit.app.school_manager.domain.service.impl.UserDetailsServiceImpl

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Autowired
    private lateinit var jwtAuthenticationFilter: JwtAuthenticationFilter

    @Bean
    fun filterChain(httpSecurity: HttpSecurity): SecurityFilterChain =
        httpSecurity.authorizeHttpRequests{ requests ->
            requests
                .requestMatchers(*PERMIT_URI).permitAll()
                .requestMatchers(HttpMethod.POST, "/login").permitAll()
                .requestMatchers(HttpMethod.POST,"/register").permitAll()
                .anyRequest().authenticated()
        }
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
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

    @Bean
    fun passwordEncode(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun provider(
        userDetailService: UserDetailsServiceImpl
    ): AuthenticationProvider = DaoAuthenticationProvider()
        .apply {
            this.setUserDetailsService(userDetailService)
            this.setPasswordEncoder(passwordEncode())
        }

    @Bean
    fun authenticationManager(config: AuthenticationConfiguration): AuthenticationManager = config.authenticationManager


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