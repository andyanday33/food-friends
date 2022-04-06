package recipesharing.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 */
@SpringBootTest
class LoginServiceTest {

    @Autowired
    LoginService loginService;
    @Test
    void userLogin() {
        loginService.userLogin("test2@st-andrews.ac.uk", "test password");
    }
}