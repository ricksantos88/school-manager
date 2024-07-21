package tech.doit.app.school_manager.domain.service

import tech.doit.app.school_manager.domain.model.dto.CreateUserDTO
import tech.doit.app.school_manager.domain.model.dto.UserDTO
import tech.doit.app.school_manager.domain.database.entities.User
import java.util.UUID

interface IUserService {
    fun createUser(user: User): UserDTO
    fun updateUser(userDTO: UserDTO, userId: UUID): UserDTO
}