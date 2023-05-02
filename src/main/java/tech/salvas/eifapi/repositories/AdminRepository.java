package tech.salvas.eifapi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tech.salvas.eifapi.models.Admin;

import java.util.Optional;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Long> {
    Optional<Admin> findByEmailOrCe(String email, String ce);
}
