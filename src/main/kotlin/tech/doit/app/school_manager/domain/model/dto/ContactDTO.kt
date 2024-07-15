package tech.doit.app.school_manager.domain.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import tech.doit.app.school_manager.domain.enums.ContactType

data class ContactDTO(
    @JsonProperty("contactType")
    val contactType: ContactType,
    @JsonProperty("value")
    val value: String
) {

}
