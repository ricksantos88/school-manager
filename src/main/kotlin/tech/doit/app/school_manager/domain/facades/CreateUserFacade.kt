package tech.doit.app.school_manager.domain.facades

import org.springframework.stereotype.Service
import tech.doit.app.school_manager.domain.enums.ProfileType
import tech.doit.app.school_manager.domain.model.dto.CreateUserDTO
import tech.doit.app.school_manager.domain.model.entities.User
import tech.doit.app.school_manager.domain.service.IUserService

@Service
class CreateUserFacade(
    private val userService: IUserService
) {

    fun createUserFromDTO(createUserDTO: CreateUserDTO) {
        val user = createAdminUser(createUserDTO)
        userService.createUser(user)
    }

    private fun createAdminUser(createUserDTO: CreateUserDTO): User {
        return User(createUserDTO, "@123", ProfileType.ADMIN)
    }

}

