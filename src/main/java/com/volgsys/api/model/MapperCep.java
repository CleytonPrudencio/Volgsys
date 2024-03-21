package com.volgsys.api.model;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {MapperCep.class})
public interface MapperCep {

    MapperCep INSTANCE = Mappers.getMapper(MapperCep.class);

    @Mapping(source = "cep", target = "cep")
    @Mapping(source = "logradouro", target = "logradouro")
    @Mapping(source = "complemento", target = "complemento")
    @Mapping(source = "bairro", target = "bairro")
    @Mapping(source = "localidade", target = "localidade")
    @Mapping(source = "uf", target = "uf")
    @Mapping(source = "ibge", target = "ibge")
    @Mapping(source = "gia", target = "gia")
    @Mapping(source = "ddd", target = "ddd")
    @Mapping(source = "siafi", target = "siafi")
    CepResponse CEP_RESPONSE (CepModel cepModel);
}
