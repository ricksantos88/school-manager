package tech.doit.app.school_manager.domain.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import tech.doit.app.school_manager.domain.database.entities.User
import java.time.LocalDateTime

data class UserDTO(

    @JsonProperty("id")
    val id: String?,
    @JsonProperty("name")
    val name: String?,
    @JsonProperty("email")
    val email: String?,
    @JsonProperty("cpf")
    val cpf: String?,
    @JsonProperty("contact")
    val contact: List<ContactDTO>?,
    @JsonProperty("address")
    val address: List<AddressDTO>?,
    @JsonProperty("createdAt")
    val createdAt: LocalDateTime?,
    @JsonProperty("updatedAt")
    val updatedAt: LocalDateTime?,
) {
    constructor(user: User) : this(
        id = user.id.toString(),
        name = user.name,
        email = user.email,
        cpf = user.cpf,
        contact = user.contacts.map { ContactDTO(it.contactType, it.value) },
        address = user.addresses.map {
            AddressDTO(it.street, it.number, it.zipCode, it.complement, it.neighborhood, it.city, it.state, it.country)
        },
        createdAt = user.createdAt,
        updatedAt = user.updatedAt
    )
}