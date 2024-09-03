package ma.transactionalstack.service;

public interface AuthenticationManualTransaction {
    void authenticateManualTransactionOperation(String username, char[] password);
}
