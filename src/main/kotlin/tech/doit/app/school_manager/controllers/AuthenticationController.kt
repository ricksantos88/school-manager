package tech.doit.app.school_manager.controllers

import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import tech.doit.app.school_manager.domain.model.dto.AuthenticationDTO
import tech.doit.app.school_manager.domain.model.dto.CreateUserDTO
import tech.doit.app.school_manager.domain.service.AuthenticationService

@RestController
class AuthenticationController(
    private val authenticationService: AuthenticationService
) {

    @PostMapping("/register")
    fun register(@RequestBody @Valid request: CreateUserDTO): ResponseEntity<*> =
        ResponseEntity.ok(authenticationService.register(request))

    @PostMapping("/auth")
    fun login(@RequestBody @Valid request: AuthenticationDTO
    ): ResponseEntity<*> = ResponseEntity.ok(authenticationService.authenticate(request))
}