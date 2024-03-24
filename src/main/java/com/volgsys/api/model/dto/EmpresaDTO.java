package com.volgsys.api.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class EmpresaDTO {
    private String cnpj;
    private Integer identificadorMatrizFilial;
    private String descricaoMatrizFilial;
    private String razaoSocial;
    private String nomeFantasia;
    private Integer situacaoCadastral;
    private String descricaoSituacaoCadastral;
    private LocalDate dataSituacaoCadastral;
    private Integer motivoSituacaoCadastral;
    private String nomeCidadeExterior;
    private Integer codigoNaturezaJuridica;
    private LocalDate dataInicioAtividade;
    private Integer cnaeFiscal;
    private String cnaeFiscalDescricao;
    private String descricaoTipoDeLogradouro;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private Integer cep;
    private String uf;
    private Integer codigoMunicipio;
    private String municipio;
    private String dddTelefone1;
    private String dddTelefone2;
    private String dddFax;
    private Integer qualificacaoDoResponsavel;
    private Double capitalSocial;
    private String porte;
    private String descricaoPorte;
    private boolean opcaoPeloSimples;
    private LocalDate dataOpcaoPeloSimples;
    private LocalDate dataExclusaoDoSimples;
    private boolean opcaoPeloMei;
    private String situacaoEspecial;
    private LocalDate dataSituacaoEspecial;
    private List<CnaeSecundarioDTO> cnaesSecundarios;
    private List<QsaDTO> qsa;

    // Getters and Setters
}

@Setter
@Getter
class CnaeSecundarioDTO {
    private Integer codigo;
    private String descricao;

    // Getters and Setters
}
@Setter
@Getter
class QsaDTO {
    private Integer identificadorDeSocio;
    private String nomeSocio;
    private String cnpjCpfDoSocio;
    private Integer codigoQualificacaoSocio;
    private Double percentualCapitalSocial;
    private LocalDate dataEntradaSociedade;
    private String cpfRepresentanteLegal;
    private String nomeRepresentanteLegal;
    private Integer codigoQualificacaoRepresentanteLegal;

    // Getters and Setters
}
