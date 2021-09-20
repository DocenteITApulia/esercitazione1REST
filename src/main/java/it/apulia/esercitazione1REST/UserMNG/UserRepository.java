package it.apulia.esercitazione1REST.UserMNG;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<Utente, Long> {

    //SELECT * FROM student WHERE email =?
    @Query("SELECT u FROM Utente u WHERE u.email = ?1")
    Optional<Utente> findUtenteByEmail(String email);
}
