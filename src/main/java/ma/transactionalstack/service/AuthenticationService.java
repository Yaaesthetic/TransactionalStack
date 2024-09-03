package ma.transactionalstack.service;


public interface AuthenticationService {
    void authenticate(String username, char[] password);
}
