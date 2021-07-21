package br.com.projeto.contratados.domain.repository.user;

import br.com.projeto.contratados.domain.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String emailUsuario);

    boolean existsByEmail(String email);
}
