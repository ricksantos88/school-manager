package tech.doit.app.school_manager.domain.model.entities

import jakarta.persistence.*
import tech.doit.app.school_manager.domain.enums.ProfileType
import tech.doit.app.school_manager.domain.model.dto.CreateUserDTO
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: UUID? = null,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false, unique = true)
    val cpf: String,

    @Column(nullable = false, unique = true)
    val email: String,

    @Column(nullable = false)
    val password: String,

    @Column(nullable = false, updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now()
) {

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    val profiles: List<UserProfile> = mutableListOf()

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    val contacts: List<Contact> = mutableListOf()

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    val addresses: List<Address> = mutableListOf()

    @PreUpdate
    fun preUpdate() {
        updatedAt = LocalDateTime.now()
    }

    constructor(dto: CreateUserDTO, encryptedPassword: String, profileType: ProfileType) : this(
        name = dto.name,
        cpf = dto.cpf,
        email = dto.email,
        password = encryptedPassword
    ) {
        profiles.plus(UserProfile(profileType = profileType, active = true, user = this))
        contacts.plus(Contact(value = dto.contact.value, contactType = dto.contact.contactType, user = this))
        addresses.plus(Address(dto.address, this))
    }
}