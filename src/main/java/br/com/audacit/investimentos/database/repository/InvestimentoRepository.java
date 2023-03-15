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
import java.util.*;

@Slf4j
@Repository
@RequiredArgsConstructor
public class InvestimentoRepository {

    private final DynamoDBMapper dynamoDBMapper;
    private final InvestimentoMapper mapper = MapperFactory.criaInstanciaMapper(InvestimentoMapper.class);

    public UUID realizarMovimentacao(Investimento investimento) {
        var entity = mapper.domainToEntity(investimento);
        dynamoDBMapper.save(entity);
        return entity.getCodigoCliente();
    }

    public BigDecimal saldoMensal(String mesSaldo) {
        Map<String, AttributeValue> parameters = new HashMap<>();
        parameters.put(":mesMovimentacao", new AttributeValue().withS(mesSaldo));

        DynamoDBQueryExpression<InvestimentoEntity> queryExpression = new DynamoDBQueryExpression<>();
        queryExpression
                .withIndexName("gsi_busca_cliente_por_mes")
                .withConsistentRead(false)
                .withKeyConditionExpression("mes_movimentacao = :mesMovimentacao")
                .withExpressionAttributeValues(parameters);

        List<InvestimentoEntity> investimentosEntity = dynamoDBMapper.query(InvestimentoEntity.class, queryExpression);

        List<BigDecimal> saldos = new ArrayList<>();
        investimentosEntity.forEach(investimento -> saldos.add(investimento.getSaldoCliente()));
        var saldoMensal = BigDecimal.ZERO;
        saldos.forEach(saldoMensal::add);
        return saldoMensal;
    }

    public void cancelarInvestimento(UUID identificacaoMovimentacao) {
        log.info("deletando_investimento: {}", identificacaoMovimentacao);
        dynamoDBMapper.delete(dynamoDBMapper.load(InvestimentoEntity.class, identificacaoMovimentacao));
    }

    public List<Investimento> buscaSaldosCliente(UUID codigoCliente) {
        Map<String, AttributeValue> parameters = new HashMap<>();
        parameters.put(":codigoCliente", new AttributeValue().withS(codigoCliente.toString()));

        DynamoDBQueryExpression<InvestimentoEntity> queryExpression = new DynamoDBQueryExpression<>();
        queryExpression
                .withConsistentRead(false)
                .withKeyConditionExpression("codigo_cliente = :codigoCliente")
                .withExpressionAttributeValues(parameters);

        List<InvestimentoEntity> investimentosEntity = dynamoDBMapper.query(InvestimentoEntity.class, queryExpression);

        List<Investimento> investimentos = new ArrayList<>();
        investimentosEntity.forEach(entity -> investimentos.add(mapper.entityToDomain(entity)));

        return investimentos;
    }

}
