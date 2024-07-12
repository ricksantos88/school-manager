package tech.doit.app.school_manager.domain.model.dto

data class UserDTO(
    val id: Long? = null,
    val name: String,
    val email: String,
    val document: String,
    val isAdult: Boolean,
    val responsibleId: Long? = null,
    val address: AddressDTO,
    val contact: ContactDTO,
    val profiles: List<UserProfileDTO>
)