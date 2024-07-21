package tech.doit.app.school_manager.domain.exceptions.enums

import org.springframework.http.HttpStatus


enum class ServiceErrorEnum(val status: HttpStatus, val message: String) {
    INVALID_CPF(HttpStatus.BAD_REQUEST, "cpf.invalid"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "user.not.found"),
    EMAIL_MUST_NOT_BE_NULL(HttpStatus.BAD_REQUEST, "email.must.not.be.null");
}