package tech.doit.app.school_manager.domain.model.dto

data class ErrorResponse(
    var httpCode: Int,
    var message: String,
)
