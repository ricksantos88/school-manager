package tech.doit.app.school_manager.domain.model.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import tech.doit.app.school_manager.domain.enums.ContactType
import java.util.UUID

@Entity
@Table(name = "contacts")
data class Contact(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: UUID? = null,

    @Column(nullable = false)
    val value: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "contact_type", nullable = false)
    val contactType: ContactType,

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val user: User
)