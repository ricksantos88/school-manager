package tech.doit.app.school_manager.domain.entities

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "addresses")
data class Address(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: UUID? = null,

    @Column(name = "street")
    val street: String,

    @Column(name = "number")
    val number: String,

    @Column(name = "zip_code")
    val zipCode: String,

    @Column(name = "neighborhood")
    val neighborhood: String,

    @Column(name = "city")
    val city: String,

    @Column(name = "state")
    val state: String
)