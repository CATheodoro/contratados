package br.com.projeto.contratados.rest.model.response.anuncioVaga;

import br.com.projeto.contratados.domain.entity.empresa.AnuncioVaga;
import br.com.projeto.contratados.rest.model.response.SetorCargoResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class AnuncioVagaDetalhadoResponse {
    private final Long id;
    private List<SetorCargoResponse> setorCargoResponses;
    private final String titulo;
    private final String descricao;
    private final String requisitos;
    private final Time cargaHoraria;
    private String salario;

    private final boolean statusAnuncio;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private final LocalDateTime dataPostagem;

    private final Long empresaId;
    private final String nomeEmpresa;
    private final String email;
    private final String celular;
    private final String telefone;

    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String numero;



    public AnuncioVagaDetalhadoResponse(AnuncioVaga anuncioVaga) {
        this.id = anuncioVaga.getId();
        if (anuncioVaga.getSetorCargo() != null)
            this.setorCargoResponses = SetorCargoResponse.converterList(anuncioVaga.getSetorCargo());
        this.titulo = anuncioVaga.getTitulo();
        this.descricao = anuncioVaga.getDescricao();

        this.requisitos = anuncioVaga.getRequisitos();
        this.cargaHoraria = anuncioVaga.getCargaHoraria();
        if(anuncioVaga.getSalario() != null)
            this.salario = anuncioVaga.getSalario().toString();

        this.statusAnuncio = anuncioVaga.isStatusAnuncio();
        this.dataPostagem = anuncioVaga.getDataPostagem();

        this.empresaId = anuncioVaga.getEmpresa().getId();
        this.nomeEmpresa = anuncioVaga.getEmpresa().getNome();
        this.email = anuncioVaga.getEmpresa().getEmail();
        this.celular = anuncioVaga.getEmpresa().getCelular();
        this.telefone = anuncioVaga.getEmpresa().getTelefone();

        if (anuncioVaga.getEndereco() != null) {
            this.cep = anuncioVaga.getEndereco().getCep();
            this.logradouro = anuncioVaga.getEndereco().getLogradouro();
            this.complemento = anuncioVaga.getEndereco().getComplemento();
            this.bairro = anuncioVaga.getEndereco().getBairro();
            this.localidade = anuncioVaga.getEndereco().getLocalidade();
            this.uf = anuncioVaga.getEndereco().getUf();
            this.numero = anuncioVaga.getEndereco().getNumero().toString();
        }

    }
}
