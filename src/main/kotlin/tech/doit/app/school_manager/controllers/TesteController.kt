package tech.doit.app.school_manager.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import tech.doit.app.school_manager.domain.model.enums.ProfileType

@RestController("/roles")
class TesteController {

    @GetMapping("/get-adm")
    fun roleAdmGet() {
        println("endpoint get acessado por ${ProfileType.ADMIN}")
    }

    @PostMapping("/post-adm")
    fun roleAdmPost() {
        println("endpoint post acessado por ${ProfileType.ADMIN}")
    }

    @GetMapping("/get-school-principal")
    fun roleSchoolPrincipalGet() {
        println("endpoint get acessado por ${ProfileType.SCHOOL_PRINCIPAL}")
    }

    @PostMapping("/post-school-principal")
    fun roleSchoolPrincipalPost() {
        println("endpoint post acessado por ${ProfileType.SCHOOL_PRINCIPAL}")
    }


}