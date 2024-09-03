package ma.transactionalstack.service.Impl;

import ma.transactionalstack.service.AuthenticationManualTransaction;
import ma.transactionalstack.service.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootTest
@EnableAspectJAutoProxy
class AuthenticationManualTransactionImplTest {

    private final AuthenticationManualTransaction authenticationManualTransaction;

    @Autowired
    public AuthenticationManualTransactionImplTest(AuthenticationManualTransaction authenticationManualTransaction) {
        this.authenticationManualTransaction = authenticationManualTransaction;
    }

    @Test
    void setAuthenticationService() {
        String username = "johndoe";
        char[] password = "examplePassword".toCharArray();

        authenticationManualTransaction.authenticateManualTransactionOperation(username,password);
    }

}