package tech.doit.app.school_manager.domain.model.dto

import tech.doit.app.school_manager.domain.enums.BranchType

data class BranchDTO(
    val id: Long? = null,
    val name: String,
    val cnpj: String,
    val type: BranchType,
    val address: AddressDTO,
    val contact: ContactDTO,
    val responsibleId: Long
)