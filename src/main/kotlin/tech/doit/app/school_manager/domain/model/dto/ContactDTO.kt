package tech.doit.app.school_manager.domain.model.dto

import tech.doit.app.school_manager.domain.enums.ContactType
import java.util.*

data class ContactDTO(
    val id: UUID? = null,
    val contactType: ContactType,
    val value: String
)
