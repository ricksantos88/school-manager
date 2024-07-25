package tech.doit.app.school_manager.infrastructure.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.nimbusds.jose.jwk.JWKSet
import com.nimbusds.jose.jwk.RSAKey
import com.nimbusds.jose.jwk.source.ImmutableJWKSet
import com.nimbusds.jose.proc.SecurityContext
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.core.authorization.OAuth2AuthorizationManagers.hasScope
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm.RS256
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.SecurityFilterChain
import tech.doit.app.school_manager.domain.model.enums.ProfileType.ADMIN
import tech.doit.app.school_manager.domain.model.enums.ProfileType.SCHOOL_PRINCIPAL
import tech.doit.app.school_manager.domain.service.impl.UserDetailsServiceImpl
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfig {
    @Value("classpath:public_key.pem")
    private lateinit var publicKey: RSAPublicKey

    @Value("classpath:private_key.pem")
    private lateinit var privateKey: RSAPrivateKey

    @Bean
    fun filterChain(httpSecurity: HttpSecurity): SecurityFilterChain =
        httpSecurity.authorizeHttpRequests{ requests ->
            requests
                .requestMatchers(*PERMIT_URI).permitAll()
                .requestMatchers(HttpMethod.POST, "/auth").permitAll()
                .requestMatchers(HttpMethod.POST,"/register").permitAll()
                .requestMatchers(HttpMethod.GET, "/roles/get-adm").access(hasScope(ADMIN.getDescription()))
                .requestMatchers(HttpMethod.POST, "/roles/post-adm").access(hasScope(ADMIN.getDescription()))
                .requestMatchers(HttpMethod.GET, "/roles/get-school-principal").access(hasScope(SCHOOL_PRINCIPAL.getDescription()))
                .requestMatchers(HttpMethod.POST, "/roles/get-school-principal").access(hasScope(SCHOOL_PRINCIPAL.getDescription()))
                .anyRequest().authenticated()
        }
        .oauth2ResourceServer { oauth ->
            oauth.jwt(Customizer.withDefaults())
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

    @Bean
    fun jwtDecoder(): JwtDecoder =
        NimbusJwtDecoder.withPublicKey(publicKey).signatureAlgorithm(RS256).build()

    @Bean
    fun jwtEncoder(): JwtEncoder =
        RSAKey.Builder(publicKey)
            .privateKey(privateKey)
            .build()
            .let(::JWKSet)
            .let{ ImmutableJWKSet<SecurityContext>(it) }
            .let(::NimbusJwtEncoder)

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