package tech.doit.app.school_manager.domain.model.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import tech.doit.app.school_manager.domain.enums.ProfileType
import java.util.UUID

@Entity
@Table(name = "user_profiles")
data class UserProfile(
    @Id
    @GeneratedValue
    val id: UUID? = null,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val profileType: ProfileType,

    @Column(nullable = false)
    val active: Boolean = true,

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val user: User
)