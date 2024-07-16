package tech.doit.app.school_manager.domain.facades

import org.springframework.stereotype.Service
import tech.doit.app.school_manager.domain.enums.ProfileType
import tech.doit.app.school_manager.domain.model.dto.CreateUserDTO
import tech.doit.app.school_manager.domain.model.dto.UserDTO
import tech.doit.app.school_manager.domain.model.entities.User
import tech.doit.app.school_manager.domain.service.IUserService

@Service
class CreateUserFacade(
    private val userService: IUserService
) {

    fun createUser(createUserDTO: CreateUserDTO): UserDTO =
        userService.createUser(createUserEntity(createUserDTO))

    private fun createUserEntity(createUserDTO: CreateUserDTO): User {
        return User(createUserDTO, createUserDTO.password, createUserDTO.userProfile)
    }

}

