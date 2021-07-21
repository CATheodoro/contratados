package br.com.projeto.contratados.domain.repository.usuario;

import br.com.projeto.contratados.domain.entity.usuario.Experiencia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienciaRepository extends JpaRepository<Experiencia, Long> {
    Page<Experiencia> findByUsuarioId(Long idUsuario, Pageable paginacao);

    Page<Experiencia> findByDescricaoAndUsuarioId(String descricao, Long idUsuario, Pageable paginacao);
}
