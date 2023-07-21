package com.loginusers.pdiaz.service.impl;

import com.loginusers.pdiaz.dto.Phone;
import com.loginusers.pdiaz.dto.SingUpUserDTO;
import com.loginusers.pdiaz.dto.SingUpUserResponseDTO;
import com.loginusers.pdiaz.service.LoginService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class LoginServiceImplTest {
    @Mock
    LoginService service;

    @InjectMocks
    LoginServiceImpl loginService;

    @Test
    void singUpNewUser() throws Exception {
        List<Phone> phones = new ArrayList<Phone>();

        when(service.singUpNewUser(any(SingUpUserDTO.class)))
                .thenReturn(new SingUpUserResponseDTO("48c44ea5-4a05-4878-bfd5-d579579150f7"
                        ,1699959991879L
                        ,1699959991879L
                        ,"eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiI0OGM0NGVhNS00YTA1LTQ4NzgtYmZkNS1kNTc5NTc5MTUwZjciLCJpYXQiOjE2ODk5NTQ2MjEsImV4cCI6MTY4OTk1NDY0M30.5O7kl0xgR4-UrEmBRyKeOpLMj1NgvJxa_NQCP_qbGnE"
                        ,Boolean.TRUE));


        assertThrows(Exception.class, () -> loginService.singUpNewUser(new SingUpUserDTO(null,"name","paulg@gmail.com","Pasword88",phones)));

    }

    @Test
    void singUpNewUser_WhenEmailIsWrong() throws Exception {
        List<Phone> phones = new ArrayList<Phone>();

        when(service.singUpNewUser(any(SingUpUserDTO.class)))
                .thenReturn(new SingUpUserResponseDTO("48c44ea5-4a05-4878-bfd5-d579579150f7"
                        ,1699959991879L
                        ,1699959991879L
                        ,"eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiI0OGM0NGVhNS00YTA1LTQ4NzgtYmZkNS1kNTc5NTc5MTUwZjciLCJpYXQiOjE2ODk5NTQ2MjEsImV4cCI6MTY4OTk1NDY0M30.5O7kl0xgR4-UrEmBRyKeOpLMj1NgvJxa_NQCP_qbGnE"
                        ,Boolean.TRUE));
        SingUpUserResponseDTO response = loginService.singUpNewUser(new SingUpUserDTO(null,"name","@gmail.com","Pasword88",phones));

        assertNotNull(response);

    }

    @Test
    public void testValidEmail() {
        assertTrue(loginService.isValidEmail("aaaaaaa@undominio.algo"));
        assertTrue(loginService.isValidEmail("info@example.com"));
        assertFalse(loginService.isValidEmail("invalid_email@.com"));
        assertFalse(loginService.isValidEmail("another.invalid_email"));
    }

    @Test
    public void testValidPassword() {
        assertTrue(loginService.isValidPassword("a2asfGfdfdf4"));
        assertTrue(loginService.isValidPassword("Abc123456"));
        assertFalse(loginService.isValidPassword("invalidpassword"));
        assertFalse(loginService.isValidPassword("short"));
        assertFalse(loginService.isValidPassword("LongPassword12345"));
    }

}