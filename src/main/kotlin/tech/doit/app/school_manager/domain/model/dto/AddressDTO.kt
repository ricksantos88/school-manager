package tech.doit.app.school_manager.domain.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotEmpty

data class AddressDTO(

    @field: NotEmpty(message = "must not be empty")
    @JsonProperty("street")
    val street: String,

    @field: NotEmpty(message = "must not be empty")
    @JsonProperty("number")
    val number: String,

    @field: NotEmpty(message = "must not be empty")
    @JsonProperty("zipCode")
    val zipCode: String,

    @field: NotEmpty(message = "must not be empty")
    @JsonProperty("complement")
    val complement: String? = null,

    @field: NotEmpty(message = "must not be empty")
    @JsonProperty("neighborhood")
    val neighborhood: String,

    @field: NotEmpty(message = "must not be empty")
    @JsonProperty("city")
    val city: String,

    @field: NotEmpty(message = "must not be empty")
    @JsonProperty("state")
    val state: String,

    @field: NotEmpty(message = "must not be empty")
    @JsonProperty("country")
    val country: String,
)