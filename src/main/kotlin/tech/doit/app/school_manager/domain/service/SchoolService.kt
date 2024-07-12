package tech.doit.app.school_manager.domain.service

import org.springframework.data.domain.Page
import tech.doit.app.school_manager.domain.model.entities.School
import java.util.UUID

interface SchoolService {

    fun createSchool(school: School)
    fun findSchoolById(schoolId: UUID): Page<School>
    fun updateSchool()
//    fun delete

}