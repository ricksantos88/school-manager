package tech.doit.app.school_manager.domain.service.impl

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import tech.doit.app.school_manager.domain.database.entities.User
import tech.doit.app.school_manager.domain.database.repositories.UserRepository
import tech.doit.app.school_manager.domain.exceptions.ServiceException
import tech.doit.app.school_manager.domain.exceptions.enums.ServiceErrorEnum
import tech.doit.app.school_manager.domain.model.dto.AuthenticationDTO
import tech.doit.app.school_manager.domain.model.dto.CreateUserDTO
import tech.doit.app.school_manager.infrastructure.security.JwtService

@Service
class AuthenticationService(
    private val passwordEncoder: PasswordEncoder,
    private val repository: UserRepository,
    private val jwtService: JwtService,
    private val authenticationManager: AuthenticationManager
) {
    fun register(request: CreateUserDTO): String {
        val secret = passwordEncoder.encode(request.password)
        val user = User(request, secret, request.userProfile)
        return repository.save(user).let(jwtService::generateToken)
    }

    fun authenticate(request: AuthenticationDTO): String {
        requireNotNull(request.username) { "É necessário informar um username" }
        checkAuthentication(request)
        return repository.findByCpf(request.username).orElseThrow{ ServiceException(ServiceErrorEnum.USER_NOT_FOUND) }
            ?.let{ user ->
                jwtService.generateToken(mapOf(
                    "roles" to user.authorities.joinToString(" ") { it.authority }
                ), user)
            }
            ?: throw UsernameNotFoundException("O usuário informado não foi encontrado")
    }

    private fun checkAuthentication(request: AuthenticationDTO) {
        UsernamePasswordAuthenticationToken(request.username, request.password)
            .also(authenticationManager::authenticate)
    }
}