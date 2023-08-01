package com.loginusers.pdiaz.service.impl;

import com.loginusers.pdiaz.dto.Phone;
import com.loginusers.pdiaz.dto.SingUpUserDTO;
import com.loginusers.pdiaz.dto.SingUpUserResponseDTO;
import com.loginusers.pdiaz.exceptions.InvalidInputException;
import com.loginusers.pdiaz.repository.UserLoginRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class LoginServiceImplTest {

    @Mock
    UserLoginRepository repository;

    @InjectMocks
    LoginServiceImpl loginService;

    @Test
    void singUpNewUser() throws Exception {
        List<Phone> phones = new ArrayList<Phone>();
        phones.add(new Phone(123456789L,56,"CL" ));
        SingUpUserResponseDTO resp = loginService
                .singUpNewUser(new SingUpUserDTO(null, "name", "paulg@gmail.com", "Pasword88", phones));
        assertNotNull(resp);

    }

    @Test
    void singUpNewUser_WhenEmailIsWrong() throws Exception {
        List<Phone> phones = new ArrayList<Phone>();

        assertThrows(Exception.class,
                () -> loginService.singUpNewUser(new SingUpUserDTO(null, "name", "@gmail.com", "Pasword88", phones)),
                "Error");

    }

    @Test
    public void testValidEmail() {
        assertTrue(loginService.isValidEmail("aaaaaaa@undominio.algo"));
        assertTrue(loginService.isValidEmail("info@example.com"));
        assertThrows(InvalidInputException.class, () ->loginService.isValidEmail("invalid_email@.com"));
        assertThrows(InvalidInputException.class, () ->loginService.isValidEmail("another.invalid_email"));

    }

    @Test
    public void testValidPassword() {
        assertTrue(loginService.isValidPassword("a2asfGfdfdf4"));
        assertTrue(loginService.isValidPassword("Abc123456"));
        assertThrows(InvalidInputException.class, () -> loginService.isValidPassword("invalidpassword"));
        assertThrows(InvalidInputException.class, () -> loginService.isValidPassword("short"));
        assertThrows(InvalidInputException.class, () -> loginService.isValidPassword("LongPassword12345"));

    }

}