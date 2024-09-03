package ma.transactionalstack.service.Impl;

import ma.transactionalstack.service.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootTest
@EnableTransactionManagement
@EnableAspectJAutoProxy
class AuthenticationServiceImplTest {

    private final AuthenticationService authenticationService;

    @Autowired
    AuthenticationServiceImplTest(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Test
    void setAuthenticationService() {
        String username = "johndoe";
        char[] password = "examplePassword".toCharArray();

        authenticationService.authenticate(username,password);
    }
}