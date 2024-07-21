package tech.doit.app.school_manager.domain.database.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import tech.doit.app.school_manager.domain.database.entities.Contact
import java.util.UUID

@Repository
interface ContactRepository: JpaRepository<Contact, UUID> {
}