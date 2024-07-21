package tech.doit.app.school_manager.domain.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import tech.doit.app.school_manager.domain.exceptions.enums.ServiceErrorEnum

class ServiceException(
    serviceError: ServiceErrorEnum
): ResponseStatusException(serviceError.status, serviceError.message) {
    val httpStatus: HttpStatus = serviceError.status
}