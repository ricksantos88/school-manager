package tech.doit.app.school_manager.domain.service.impl

import org.springframework.stereotype.Service
import tech.doit.app.school_manager.domain.model.dto.UserDTO
import tech.doit.app.school_manager.domain.database.entities.User
import tech.doit.app.school_manager.domain.database.repositories.UserRepository
import tech.doit.app.school_manager.domain.service.IUserService
import java.util.*

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
): IUserService {
    override fun createUser(user: User): UserDTO = UserDTO(saveUser(user))

    override fun updateUser(userDTO: UserDTO, userId: UUID): UserDTO {
        TODO("Not yet implemented")
    }

    private fun saveUser(user: User): User =  userRepository.save(user)
}