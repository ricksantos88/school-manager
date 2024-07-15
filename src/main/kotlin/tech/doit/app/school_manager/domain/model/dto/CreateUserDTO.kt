package tech.doit.app.school_manager.domain.model.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class CreateUserDTO(
    @JsonProperty("name")
    val name: String,
    @JsonProperty("email")
    val email: String,
    @JsonProperty("cpf")
    val cpf: String,
    @JsonProperty("password")
    val password: String,
    @JsonProperty("contact")
    val contact: ContactDTO,
    @JsonProperty("address")
    val address: AddressDTO
)
