package br.com.audacit.investimentos.mapper;

import br.com.audacit.investimentos.database.entity.InvestimentoEntity;
import br.com.audacit.investimentos.dto.in.InvestimentoRequest;
import br.com.audacit.investimentos.dto.in.RetiradaRequest;
import br.com.audacit.investimentos.model.Investimento;
import org.mapstruct.Mapper;

@Mapper
public interface InvestimentoMapper {

    Investimento investimentoRequestToDomain(InvestimentoRequest request);

    Investimento retiradaRequestToDomain(RetiradaRequest request);

    InvestimentoEntity domainToEntity(Investimento investimento);

    Investimento entityToDomain(InvestimentoEntity entity);
}
