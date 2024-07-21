package tech.doit.app.school_manager.framework.aspects

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import tech.doit.app.school_manager.domain.exceptions.ServiceException
import tech.doit.app.school_manager.domain.exceptions.enums.ServiceErrorEnum
import tech.doit.app.school_manager.domain.model.enums.ProfileType
import tech.doit.app.school_manager.domain.model.security.UserProfileAuthority
import tech.doit.app.school_manager.framework.annotations.AuthorizedRoles

@Aspect
@Component
class ValidateRoleAspect {

    @Before("@annotation(com.doit.project.village.manager.modules.security.annotation.AuthorizedRoles)")
    fun checkAuthorization(joinPoint: JoinPoint) {
        val roles = findRoles(joinPoint)
        val security = SecurityContextHolder.getContext()
        val authentication = security.authentication
        val authorities = authentication.authorities as List<UserProfileAuthority>
        if(authorities.isNotEmpty()) {
            if (!authorities.any {
                    roles.map(UserProfileAuthority::fromRole).contains(it.authority)
                }) {
                throw ServiceException(ServiceErrorEnum.USER_NOT_FOUND)
            }
        } else {
            throw ServiceException(ServiceErrorEnum.THE_USER_NOT_AUTHENTICATED)
        }
    }

    fun findRoles(joinPoint: JoinPoint): Array<ProfileType> {
        val method = joinPoint.signature as MethodSignature
        val annotation = method.method.getAnnotation(AuthorizedRoles::class.java)
        return annotation.value
    }
}