package tech.doit.app.school_manager.domain.model.entities

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: UUID? = null,

    @Column(name = "name")
    val name: String,

    @Column(name = "email")
    val email: String,

    @Column(name = "password")
    val password: String,

    @Column(name = "document")
    val document: String,

    @Column(name = "is_adult")
    val isAdult: Boolean,

    @ManyToOne
    @JoinColumn(name = "responsible_user_id", nullable = true)
    val responsible: User? = null,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    val address: Address,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "contact_id", referencedColumnName = "id")
    val contact: Contact,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val profiles: List<UserProfile> = mutableListOf()
)