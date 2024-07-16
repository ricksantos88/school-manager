package tech.doit.app.school_manager.domain.service.impl

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import tech.doit.app.school_manager.domain.model.dto.CreateUserDTO
import tech.doit.app.school_manager.domain.repositories.UserRepository
import tech.doit.app.school_manager.utils.UserUtils

@ExtendWith(MockitoExtension::class)
class UserServiceImplTest {

    @Mock
    private lateinit var userRepository: UserRepository

    @InjectMocks
    private lateinit var userServiceImpl: UserServiceImpl

    private lateinit var createIUserDTO: CreateUserDTO

    @BeforeEach
    fun setUp() {
        createIUserDTO = UserUtils.getCreateUserDTO()
    }
}