package tech.doit.app.school_manager.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import tech.doit.app.school_manager.exceptions.enums.ServiceErrorEnum

class ServiceException(
    serviceError: ServiceErrorEnum
): ResponseStatusException(serviceError.status, serviceError.message) {
    val httpStatus: HttpStatus = serviceError.status
}