package tech.doit.app.school_manager.framework.annotations

import tech.doit.app.school_manager.domain.model.enums.ProfileType

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
annotation class AuthorizedRoles(
    val value: Array<ProfileType>
)