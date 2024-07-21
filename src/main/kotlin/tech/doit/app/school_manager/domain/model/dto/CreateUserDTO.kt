package tech.doit.app.school_manager.domain.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import tech.doit.app.school_manager.domain.model.enums.ProfileType

data class CreateUserDTO(

    @field: NotEmpty(message = "must not be empty")
    @JsonProperty("name")
    val name: String,

    @field: Email(message = "must be valid")
    @field: NotEmpty(message = "must not be empty")
    @JsonProperty("email")
    val email: String,

    @field: NotEmpty(message = "must not be empty")
    @JsonProperty("cpf")
    val cpf: String,

    @field: NotEmpty(message = "must not be empty")
    @JsonProperty("password")
    val password: String,

    @JsonProperty("contact")
    val contact: ContactDTO,

    @JsonProperty("address")
    val address: AddressDTO,

    @JsonProperty("profile")
    val userProfile: ProfileType
)
