package br.com.projeto.contratados.domain.repository.empresa;

import br.com.projeto.contratados.domain.entity.empresa.SetorCargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SetorCargoRepository extends JpaRepository<SetorCargo, Long> {
}
