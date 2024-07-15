package tech.doit.app.school_manager.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.validation.FieldError
import org.springframework.web.server.ResponseStatusException
import tech.doit.app.school_manager.domain.model.dto.ErrorResponse

class ApiErrors {

    private var errors: MutableList<ErrorResponse> = ArrayList()

    constructor(fieldsErrors: List<FieldError>, httpStatus: HttpStatus) {
        fieldsErrors.forEach {
            val rejectedValue = it.rejectedValue ?: ""
            val defaultMessage = "${it.field} $rejectedValue ${it.defaultMessage}"
            errors.add(generateErrorResponse(httpStatus, defaultMessage))
        }
    }

    constructor(responseStatusException: ResponseStatusException) {
        responseStatusException.reason?.let {
            val message = responseStatusException.reason
            errors.add(generateErrorResponse(responseStatusException.statusCode, message!!))
        }
    }

    fun getErrors(): List<ErrorResponse?> = errors

    private fun generateErrorResponse(httpStatus: HttpStatus, message: String): ErrorResponse =
        ErrorResponse(httpStatus.value(), message)

    private fun generateErrorResponse(httpStatus: HttpStatusCode, message: String): ErrorResponse =
        ErrorResponse(httpStatus.value(), message)

}
