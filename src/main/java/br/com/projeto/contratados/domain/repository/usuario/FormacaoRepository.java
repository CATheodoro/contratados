package br.com.projeto.contratados.domain.repository.usuario;

import br.com.projeto.contratados.domain.entity.usuario.Formacao;
import br.com.projeto.contratados.domain.entity.usuario.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormacaoRepository extends JpaRepository<Formacao, Long> {
    
    Page<Formacao> findByUsuarioId(Long idUsuario, Pageable paginacao);

    Page<Formacao> findByDescricaoAndUsuarioId(String descricao, Long idUsuario, Pageable paginacao);

    boolean existsByUsuarioId(Long idUsuario);
}
