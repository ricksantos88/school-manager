package tech.doit.app.school_manager.domain.repositories

import org.springframework.data.jpa.repository.JpaRepository
import tech.doit.app.school_manager.domain.model.entities.UserProfile
import java.util.UUID

interface UserProfileRepository: JpaRepository<UserProfile, UUID> {
}