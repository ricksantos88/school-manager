package tech.doit.app.school_manager.domain.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotEmpty
import tech.doit.app.school_manager.domain.model.enums.ContactType

data class ContactDTO(
    @JsonProperty("contactType")
    val contactType: ContactType,

    @field: NotEmpty(message = "must not be empty")
    @JsonProperty("value")
    val value: String
) {

}
