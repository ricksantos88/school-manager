package tech.doit.app.school_manager.controllers

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import tech.doit.app.school_manager.domain.facades.CreateUserFacade
import tech.doit.app.school_manager.domain.model.dto.CreateUserDTO
import tech.doit.app.school_manager.domain.model.dto.UserDTO

@RestController
@RequestMapping("/users")
class UserController(
    private val createUserFacade: CreateUserFacade
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun createAuthor(@RequestBody @Valid createUserDTO: CreateUserDTO): UserDTO =
        createUserFacade.createUser(createUserDTO)

}