package tech.doit.app.school_manager.domain.exceptions.enums

import org.springframework.http.HttpStatus


enum class ServiceErrorEnum(val status: HttpStatus, val message: String) {
    INVALID_CPF(HttpStatus.BAD_REQUEST, "cpf.invalid"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "user.not.found"),
    EMAIL_MUST_NOT_BE_NULL(HttpStatus.BAD_REQUEST, "email.must.not.be.null"),
    USER_DOES_NOT_HAVE_PERMISSION_TO_THE_ACTION(HttpStatus.FORBIDDEN, "the.user.does.not.have.permission.to.perform.the.action."),
    THE_USER_NOT_AUTHENTICATED(HttpStatus.UNAUTHORIZED, "the.user.not.authenticated");
}