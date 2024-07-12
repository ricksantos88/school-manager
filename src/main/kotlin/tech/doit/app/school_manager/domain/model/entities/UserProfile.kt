package tech.doit.app.school_manager.domain.model.entities

import jakarta.persistence.*
import tech.doit.app.school_manager.domain.enums.ProfileType
import java.util.UUID

@Entity
@Table(name = "user_profiles")
data class UserProfile(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: UUID? = null,

    @Column(name = "profile")
    @Enumerated(EnumType.STRING)
    val profile: ProfileType,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User,

    @Column(name = "active")
    var active: Boolean = true
)