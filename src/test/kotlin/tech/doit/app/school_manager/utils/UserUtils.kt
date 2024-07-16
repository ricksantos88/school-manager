package tech.doit.app.school_manager.utils

import tech.doit.app.school_manager.domain.enums.ContactType
import tech.doit.app.school_manager.domain.enums.ProfileType
import tech.doit.app.school_manager.domain.model.dto.AddressDTO
import tech.doit.app.school_manager.domain.model.dto.ContactDTO
import tech.doit.app.school_manager.domain.model.dto.CreateUserDTO

class UserUtils {

    companion object {
        fun getCreateUserDTO(): CreateUserDTO {
            return CreateUserDTO(
                name = "John Doe",
                email = "johndoe@example.com",
                cpf = "00166902021",
                password = "mysecretpassword",
                contact = ContactDTO(contactType = ContactType.MOBILE, value = "1234567890"),
                address = AddressDTO(
                    street = "Main Street",
                    number = "123",
                    zipCode = "66567300",
                    complement = "not",
                    neighborhood = "Downtown",
                    city = "New York",
                    state = "NY",
                    country = "USA"
                ),
                userProfile = ProfileType.ADMIN
            )
        }
    }

}