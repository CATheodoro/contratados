package br.com.projeto.contratados.domain.repository.empresa;

import br.com.projeto.contratados.domain.entity.empresa.Empresa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {

    Page<Empresa> findByNomeFantasia(String nomeFantasia, Pageable paginacao);


    Optional<Empresa> findByAnuncioVagaId(Integer id);
}
