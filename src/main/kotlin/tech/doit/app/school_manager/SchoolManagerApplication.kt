package tech.doit.app.school_manager

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SchoolManagerApplication

fun main(args: Array<String>) {
	runApplication<SchoolManagerApplication>(*args)
}
