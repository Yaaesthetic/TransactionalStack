package ma.transactionalstack.service.Impl;

import ma.transactionalstack.model.AuthedPerson;
import ma.transactionalstack.repository.AuthedRepo;
import ma.transactionalstack.service.AuthenticationManualTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.IllegalTransactionStateException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.util.Arrays;

@Service
public class AuthenticationManualTransactionImpl implements AuthenticationManualTransaction {

    private final PlatformTransactionManager transactionManager;
    private final TransactionTemplate transactionTemplate;
    private final AuthedRepo authedRepo;

    @Autowired
    public AuthenticationManualTransactionImpl(PlatformTransactionManager transactionManager, TransactionTemplate transactionTemplate, AuthedRepo authedRepo) {
        this.transactionManager = transactionManager;
        this.transactionTemplate = transactionTemplate;

        this.authedRepo = authedRepo;
    }

    @Override
    public void authenticateManualTransactionOperation(String username, char[] password) {
        transactionTemplate.setTransactionManager(transactionManager);
        //TransactionTemplate relies on a TransactionManager to control transaction boundaries
        //such as begin, commit, and rollback operations.
        transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
        transactionTemplate.setTimeout(30); // 30 seconds timeout
        transactionTemplate.setReadOnly(true);
        transactionTemplate.execute(status -> {
            try {

                SecureRandom random = new SecureRandom();
                byte[] salt = new byte[16]; // 16 bytes is a common length for a salt

                for (int i = 0; i < 10; i++) {
                    // Generates a random salt
                    random.nextBytes(salt);
                    if (i == 7)
                        hashPassword(null, null, username);
                    else
                        hashPassword(password, salt, username);

                }

                // Clear sensitive data from memory

                Arrays.fill(password, '\0');
                return null;
            } catch (Exception e) {
                // Rollback transaction
                status.setRollbackOnly();
                throw e;
            }
        });
    }

    protected void hashPassword(char[] password, byte[] salt, String username) {
        try {
            // Number of iterations for the PBKDF2 algorithm. Higher iterations slow down brute-force attacks.
            int iterations = 10000;
            // The length of the derived key (hash) in bits. 256 bits (32 bytes) is a standard and secure length.
            int keyLength = 256;
            // Combines the password, salt, number of iterations, and key length into a specification object for PBKDF2.
            PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, keyLength);
            // Initializes the SecretKeyFactory with the PBKDF2 algorithm using HMAC-SHA256 for hashing.
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            // Generates and returns the derived key (hash) as a byte array based on the provided specification.
            System.out.println("hashedPassword : " + Arrays.toString(skf.generateSecret(spec).getEncoded()));

            byte[] hashedPassword = skf.generateSecret(spec).getEncoded();

            authedRepo.save(AuthedPerson.builder()
                    .username(username)
                    .salt(salt)
                    .hashedCode(hashedPassword)
                    .build());


        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalTransactionStateException("trigger the rollback");
        }
    }
}
