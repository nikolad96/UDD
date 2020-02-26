package udd.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import udd.server.model.Rad;
import udd.server.model.Recenzent;

@Repository
public interface RecenzentRepository extends JpaRepository<Recenzent, Long> {
    Recenzent findOneById(Long Id);

}
