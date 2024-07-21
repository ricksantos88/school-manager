package tech.doit.app.school_manager.domain.model.dto

import jakarta.validation.constraints.NotBlank

data class AuthenticationDTO(
    @NotBlank(message = "this field is required")
    val username: String?,
    @NotBlank(message = "this field is required")
    val password: String?
)
