package tech.doit.app.school_manager.domain.model.dto

data class SchoolDTO(
    val id: Long? = null,
    val name: String,
    val cnpj: String,
    val responsibleId: Long,
    val branches: List<BranchDTO>
)