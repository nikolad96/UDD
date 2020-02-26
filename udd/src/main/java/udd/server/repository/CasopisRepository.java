package udd.server.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import udd.server.model.Casopis;

import java.util.List;

@Repository
public interface CasopisRepository extends JpaRepository<Casopis, Long> {
    Casopis findOneById(Long Id);
    List<Casopis> findAll();
    void deleteById(Long aLong);
}
