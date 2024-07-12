package tech.doit.app.school_manager.domain.entities

import jakarta.persistence.*
import tech.doit.app.school_manager.domain.enums.BranchType
import java.util.*

@Entity
@Table(name = "branches")
data class Branch(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: UUID? = null,

    @Column(name = "name")
    val name: String,

    @Column(name = "cnpj")
    val cnpj: String,

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    val type: BranchType,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    val address: Address,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "contact_id", referencedColumnName = "id")
    val contact: Contact,

    @ManyToOne
    @JoinColumn(name = "responsible_user_id")
    val responsible: User,

    @ManyToOne
    @JoinColumn(name = "school_id")
    val school: School
)