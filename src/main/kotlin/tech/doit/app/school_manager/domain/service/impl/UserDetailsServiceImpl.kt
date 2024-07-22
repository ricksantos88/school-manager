package tech.doit.app.school_manager.domain.service.impl

import jakarta.transaction.Transactional
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import tech.doit.app.school_manager.domain.database.repositories.UserRepository
import tech.doit.app.school_manager.domain.exceptions.ServiceException
import tech.doit.app.school_manager.domain.exceptions.enums.ServiceErrorEnum

@Service
class UserDetailsServiceImpl(
    private val userRepository: UserRepository
): UserDetailsService {

    @Transactional
    override fun loadUserByUsername(username: String?): UserDetails {
        requireNotNull(username) { "The username is required" }
        return userRepository.findByCpf(username).orElseThrow { ServiceException(ServiceErrorEnum.USER_NOT_FOUND) }
    }
}