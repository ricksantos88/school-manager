package tech.doit.app.school_manager.domain.model.dto

data class AddressDTO(
    val id: Long? = null,
    val street: String,
    val number: String,
    val zipCode: String,
    val neighborhood: String,
    val city: String,
    val state: String,

)