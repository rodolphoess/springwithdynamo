package br.com.audacit.investimentos.mapper;

import org.mapstruct.factory.Mappers;

public class MapperFactory {

    private MapperFactory() { }

    public static <T> T criaInstanciaMapper(final Class<T> classe) {
        return Mappers.getMapper(classe);
    }

}
