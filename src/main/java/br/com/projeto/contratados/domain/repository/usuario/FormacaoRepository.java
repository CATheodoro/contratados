package br.com.projeto.contratados.domain.repository.usuario;

import br.com.projeto.contratados.domain.entity.usuario.Formacao;
import br.com.projeto.contratados.domain.entity.usuario.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormacaoRepository extends JpaRepository<Formacao, Integer> {
    Page<Formacao> findByDescricao(String descricao, Pageable paginacao);

    boolean existsByUsuario(Usuario usuario);
}
