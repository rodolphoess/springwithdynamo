package br.com.audacit.investimentos.mapper;

import br.com.audacit.investimentos.database.entity.InvestimentoEntity;
import br.com.audacit.investimentos.dto.in.InvestimentoRequest;
import br.com.audacit.investimentos.dto.in.RetiradaRequest;
import org.mapstruct.Mapper;

@Mapper
public interface InvestimentoMapper {

    InvestimentoEntity investimentoRequestToEntity(InvestimentoRequest request);

    InvestimentoEntity retiradaRequestToEntity(RetiradaRequest request);

}
