package tech.doit.app.school_manager.advicers

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import tech.doit.app.school_manager.domain.exceptions.ApiErrors
import tech.doit.app.school_manager.domain.exceptions.ServiceException

@RestControllerAdvice
class BusinessAdvice {

    @ExceptionHandler(ServiceException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleServiceException(serviceException: ServiceException): ApiErrors = ApiErrors(serviceException)

}