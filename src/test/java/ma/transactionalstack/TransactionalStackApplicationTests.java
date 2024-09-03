package ma.transactionalstack;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootTest
@EnableTransactionManagement
@EnableAspectJAutoProxy
class TransactionalStackApplicationTests {

    @Test
    void contextLoads() {
    }

}
