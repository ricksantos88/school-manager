package tech.doit.app.school_manager.controllers

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import tech.doit.app.school_manager.domain.model.enums.ProfileType

@RestController
@RequestMapping("/roles")
class TesteController {

    @GetMapping("/get-adm")
    fun roleAdmGet() {
        val principal = SecurityContextHolder.getContext().authentication.principal
        println("endpoint post acessado por ${ProfileType.ADMIN}")
        println((principal as Jwt).claims["scope"])
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