package tech.doit.app.school_manager.domain.model.security

import org.springframework.security.core.GrantedAuthority
import tech.doit.app.school_manager.domain.database.entities.UserProfile
import tech.doit.app.school_manager.domain.model.enums.ProfileType

data class UserProfileAuthority(
    private val userProfile: UserProfile
): GrantedAuthority {
    override fun getAuthority(): String = userProfile.profileType.getDescription()
    companion object {
        fun fromRole(profileType: ProfileType) = profileType.getDescription()
    }
}