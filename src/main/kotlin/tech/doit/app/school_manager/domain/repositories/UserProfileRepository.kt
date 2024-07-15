package tech.doit.app.school_manager.domain.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import tech.doit.app.school_manager.domain.model.entities.UserProfile
import java.util.UUID

@Repository
interface UserProfileRepository: JpaRepository<UserProfile, UUID> {
}