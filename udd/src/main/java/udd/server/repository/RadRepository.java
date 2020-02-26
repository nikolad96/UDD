package udd.server.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import udd.server.model.Rad;

import java.util.List;


@Repository
public interface RadRepository extends JpaRepository<Rad, Long> {

    Rad findOneById(Long Id);
    Rad findOneByNaziv(String naslov);
    List<Rad> findAll();
    void deleteById(Long aLong);
}
