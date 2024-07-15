package tech.doit.app.school_manager.domain.service

import tech.doit.app.school_manager.domain.model.dto.CreateUserDTO
import tech.doit.app.school_manager.domain.model.dto.UserDTO
import java.util.UUID

interface IUserService {
    fun createUser(createUserDTO: CreateUserDTO): UserDTO
    fun updateUser(userDTO: UserDTO, userId: UUID): UserDTO
}