package tech.doit.app.school_manager.domain.database.entities

import jakarta.persistence.*
import jakarta.persistence.FetchType.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import tech.doit.app.school_manager.domain.model.enums.ProfileType
import tech.doit.app.school_manager.domain.model.dto.CreateUserDTO
import tech.doit.app.school_manager.domain.model.security.UserProfileAuthority
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
    private val password: String,

    @Column(nullable = false, updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now()
): UserDetails {

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = EAGER)
    val profiles: MutableList<UserProfile> = mutableListOf()

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = EAGER)
    val contacts: MutableList<Contact> = mutableListOf()

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = EAGER)
    val addresses: MutableList<Address> = mutableListOf()

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
        profiles.add(UserProfile(profileType = profileType, active = true, user = this))
        contacts.add(Contact(value = dto.contact.value, contactType = dto.contact.contactType, user = this))
        addresses.add(Address(dto.address, this))
    }

    override fun getAuthorities(): List<UserProfileAuthority> = profiles.map(::UserProfileAuthority)

    override fun getPassword(): String = this.password

    override fun getUsername(): String = this.cpf

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}