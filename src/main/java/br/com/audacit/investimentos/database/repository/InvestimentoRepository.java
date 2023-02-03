package br.com.audacit.investimentos.database.repository;

import br.com.audacit.investimentos.database.entity.InvestimentoEntity;
import br.com.audacit.investimentos.mapper.InvestimentoMapper;
import br.com.audacit.investimentos.mapper.MapperFactory;
import br.com.audacit.investimentos.model.Investimento;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Repository
@RequiredArgsConstructor
public class InvestimentoRepository {

    private final DynamoDBMapper dynamoDBMapper;
    private final InvestimentoMapper mapper = MapperFactory.criaInstanciaMapper(InvestimentoMapper.class);

    public UUID injetarDinheiro(Investimento investimento) {
        var entity = mapper.domainToEntity(investimento);
        dynamoDBMapper.save(entity);
        return entity.getCodigoCliente();
    }

    public UUID retirarDinheiro(Investimento investimento) {
        var entity = mapper.domainToEntity(investimento);
        dynamoDBMapper.save(entity);
        return entity.getCodigoCliente();
    }

    public BigDecimal saldoMensal(String mesSaldo) {
        return BigDecimal.ONE;
    }

    public void cancelarInvestimento(UUID identificacaoMovimentacao) {
        log.info("deletando_investimento: {}", identificacaoMovimentacao);
        dynamoDBMapper.delete(dynamoDBMapper.load(InvestimentoEntity.class, identificacaoMovimentacao));
    }

    public BigDecimal buscaUltimoSaldo(UUID codigoCliente) {
        Map<String, AttributeValue> parameters = new HashMap<>();
        parameters.put(":codigoCliente", new AttributeValue().withS(codigoCliente.toString()));

        DynamoDBQueryExpression<InvestimentoEntity> queryExpression = new DynamoDBQueryExpression<>();
        queryExpression
                .withIndexName("")
                .withConsistentRead(false)
                .withKeyConditionExpression("")
                .withFilterExpression("")
                .withExpressionAttributeValues(parameters);

        InvestimentoEntity investimentoEntity = dynamoDBMapper.query(InvestimentoEntity.class, queryExpression).get(0);

        return investimentoEntity.getSaldoCliente();
    }

}
