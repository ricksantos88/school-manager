package tech.doit.app.school_manager.advicers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.server.ResponseStatusException
import tech.doit.app.school_manager.domain.exceptions.ApiErrors

@RestControllerAdvice
class RestControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    fun handleValidationException(argumentNotValidException: MethodArgumentNotValidException): ApiErrors? {
        val fieldsErrors = argumentNotValidException.fieldErrors
        return ApiErrors(fieldsErrors, HttpStatus.UNPROCESSABLE_ENTITY)
    }

    @ExceptionHandler(ResponseStatusException::class)
    fun handleResponseStatusException(responseStatusException: ResponseStatusException): ResponseEntity<*>? {
        return ResponseEntity(ApiErrors(responseStatusException), responseStatusException.statusCode)
    }

}
