package br.com.projeto.contratados.domain.repository.empresa;

import br.com.projeto.contratados.domain.entity.empresa.AnuncioVaga;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnuncioVagaRepository extends JpaRepository<AnuncioVaga, Long> {

    Page<AnuncioVaga> findByEmpresaId(Long idEmpresa, Pageable paginacao);
    
    Page<AnuncioVaga> findAllByEnderecoLocalidadeAndSetorCargoCargo(String localidade, String cargo, Pageable paginacao);

    Page<AnuncioVaga> findAllByEnderecoLocalidade(String localidade, Pageable paginacao);

    Page<AnuncioVaga> findAllBySetorCargoCargo(String cargo, Pageable paginacao);

    Page<AnuncioVaga> findAllByEmpresaId(Long idEmpresaSemValidacao, Pageable paginacao);

    Page<AnuncioVaga> findAllByEmpresaIdAndEnderecoLocalidadeAndSetorCargoCargo(Long idEmpresaSemValidacao, String localidade, String cargo, Pageable paginacao);

    Page<AnuncioVaga> findAllByEmpresaIdAndEnderecoLocalidade(Long idEmpresaSemValidacao, String localidade, Pageable paginacao);

    Page<AnuncioVaga> findAllByEmpresaIdAndSetorCargoCargo(Long idEmpresaSemValidacao, String cargo, Pageable paginacao);
}
