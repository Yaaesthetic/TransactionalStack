package ma.transactionalstack.repository;

import ma.transactionalstack.model.AuthedPerson;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthedRepo extends CrudRepository<AuthedPerson,Integer> {
}
