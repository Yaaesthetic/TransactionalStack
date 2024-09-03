package ma.transactionalstack.service.Impl;


import ma.transactionalstack.model.AuthedPerson;
import ma.transactionalstack.repository.AuthedRepo;
import ma.transactionalstack.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.IllegalTransactionStateException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.util.Arrays;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthedRepo authedRepo;

    @Autowired
    public AuthenticationServiceImpl(AuthedRepo authedRepo) {
        this.authedRepo = authedRepo;
    }

    @Override

    @Transactional(rollbackFor = RuntimeException.class, propagation = Propagation.REQUIRED)
    public void authenticate(String username, char[] password) {

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

    }

    // PBKDF2, bcrypt, or Argon2.
    // These functions are designed specifically for hashing passwords
    //@Transactional(propagation = Propagation.REQUIRES_NEW)
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
            throw new RuntimeException("rolled the rollback for the transaction");
            // Transaction is rolled back by default
            // (if the exception is a runtime exception or an unchecked exception)
        }
    }
}