package tech.doit.app.school_manager.domain.entities

import jakarta.persistence.*
import tech.doit.app.school_manager.domain.enums.ContactType
import java.util.*

@Entity
@Table(name = "contacts")
data class Contact(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: UUID? = null,

    @Column(name = "contact_type")
    @Enumerated(EnumType.STRING)
    val contactType: ContactType,

    @Column(name = "value")
    val value: String
)