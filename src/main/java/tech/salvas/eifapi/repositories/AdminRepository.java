package tech.salvas.eifapi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tech.salvas.eifapi.models.Admin;
import tech.salvas.eifapi.models.Student;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Long> {


    Optional<Admin> findAdminByEmailAndPassword(String email, String password);

    Optional<Admin> findAdminByCeAndPassword(String ce, String password);
}
