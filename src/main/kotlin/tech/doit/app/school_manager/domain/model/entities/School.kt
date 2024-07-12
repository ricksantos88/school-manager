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
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "schools")
data class School(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: UUID? = null,

    @Column(name = "name")
    val name: String,

    @Column(name = "cnpj")
    val cnpj: String,

    @ManyToOne
    @JoinColumn(name = "responsible_user_id")
    val responsible: User,

    @OneToMany(mappedBy = "school", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val branches: List<Branch> = mutableListOf()
)