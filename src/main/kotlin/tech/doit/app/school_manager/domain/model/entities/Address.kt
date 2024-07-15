package tech.doit.app.school_manager.domain.model.entities

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "addresses")
data class Address(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: UUID? = null,

    @Column(nullable = false)
    val street: String,

    @Column(name = "zip_code",nullable = false)
    val zipCode: String,

    @Column(nullable = false)
    val number: String,

    val complement: String? = null,

    @Column(nullable = false)
    val neighborhood: String,

    @Column(nullable = false)
    val city: String,

    @Column(nullable = false)
    val state: String,

    @Column(nullable = false)
    val country: String,

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val user: User
)