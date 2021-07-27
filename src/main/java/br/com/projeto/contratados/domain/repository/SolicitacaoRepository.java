package br.com.projeto.contratados.domain.repository;

import br.com.projeto.contratados.domain.entity.empresa.Empresa;
import br.com.projeto.contratados.domain.entity.solicitacao.Solicitacao;
import br.com.projeto.contratados.domain.entity.solicitacao.SolicitacaoEmpresaStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long> {

    Page<Solicitacao> findAllByUsuarioId(Long idUsuario, Pageable paginacao);

    Page<Solicitacao> findAllByUsuarioIdAndSolicitacaoEmpresaStatus(Long idUsuario, SolicitacaoEmpresaStatus status, Pageable paginacao);

    Page<Solicitacao> findByAnuncioVagaEmpresaId(Long idEmpresa, Pageable paginacao);

    Page<Solicitacao> findByAnuncioVagaEmpresaIdAndSolicitacaoEmpresaStatus(Long idEmpresaSemValidacao, SolicitacaoEmpresaStatus status, Pageable paginacao);

    Page<Solicitacao> findByAnuncioVagaEmpresaIdAndAnuncioVagaId(Long idEmpresaSemValidacao, Long anuncioId, Pageable paginacao);


    boolean existsByAnuncioVagaIdAndUsuarioId(Long anuncioVagaId, Long idUsuario);
}
