package br.com.projeto.contratados.domain.repository.usuario;

import br.com.projeto.contratados.domain.entity.usuario.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Page<Usuario> findByNome(String nome, Pageable pageable);

    boolean existsByEmail(String email);
}
