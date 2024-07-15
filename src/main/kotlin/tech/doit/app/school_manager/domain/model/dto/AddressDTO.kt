package tech.doit.app.school_manager.domain.model.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class AddressDTO(
    @JsonProperty("street")
    val street: String,
    @JsonProperty("number")
    val number: String,
    @JsonProperty("zipCode")
    val zipCode: String,
    @JsonProperty("complement")
    val complement: String? = null,
    @JsonProperty("neighborhood")
    val neighborhood: String,
    @JsonProperty("city")
    val city: String,
    @JsonProperty("state")
    val state: String,
    @JsonProperty("country")
    val country: String,
)