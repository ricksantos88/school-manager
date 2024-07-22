package tech.doit.app.school_manager.domain.service

import tech.doit.app.school_manager.domain.model.dto.AuthenticationDTO
import tech.doit.app.school_manager.domain.model.dto.CreateUserDTO

interface IAuthenticationService {
    fun register(request: CreateUserDTO): String
    fun authenticate(request: AuthenticationDTO): String
}