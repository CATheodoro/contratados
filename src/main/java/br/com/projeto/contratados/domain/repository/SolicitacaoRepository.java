package br.com.projeto.contratados.domain.repository;

import br.com.projeto.contratados.domain.entity.solicitacao.Solicitacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long> {
    boolean existsByUsuarioId(Long id);

    boolean existsByAnuncioVagaId(Long id);
}
