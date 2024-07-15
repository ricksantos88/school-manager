package tech.doit.app.school_manager.domain.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class UserDTO(
    @JsonProperty("name")
    val name: String,
    @JsonProperty("email")
    val email: String,
    @JsonProperty("cpf")
    val cpf: String,
    @JsonProperty("contact")
    val contact: ContactDTO,
    @JsonProperty("address")
    val address: AddressDTO,
    @JsonProperty("createdAt")
    val createdAt: LocalDateTime,
    @JsonProperty("updatedAt")
    val updatedAt: LocalDateTime,
)