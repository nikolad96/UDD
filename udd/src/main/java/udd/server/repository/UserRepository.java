package udd.server.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import udd.server.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

        User findOneById(Long Id);
        User findOneByEmail(String email);
        User findOneByUsername(String username);
        void deleteById(Long aLong);

}
