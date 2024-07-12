package tech.doit.app.school_manager.domain.repositories

import org.springframework.data.jpa.repository.JpaRepository
import tech.doit.app.school_manager.domain.model.entities.Contact
import java.util.UUID

interface ContactRepository: JpaRepository<Contact, UUID> {
}