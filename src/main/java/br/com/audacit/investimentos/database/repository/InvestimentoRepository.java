package br.com.audacit.investimentos.database.repository;

import br.com.audacit.investimentos.mapper.InvestimentoMapper;
import br.com.audacit.investimentos.mapper.MapperFactory;
import br.com.audacit.investimentos.model.Investimento;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.UUID;

@Slf4j
@Repository
@RequiredArgsConstructor
public class InvestimentoRepository {

    private final DynamoDBMapper dynamoDBMapper;
    private final InvestimentoMapper mapper = MapperFactory.criaInstanciaMapper(InvestimentoMapper.class);

    public UUID injetarDinheiro(Investimento investimento) {
        log.info("injetando_dinheiro");
        var entity = mapper.domainToEntity(investimento);
        dynamoDBMapper.save(entity);
        return entity.getCodigoCliente();
    }

    public UUID retirarDinheiro(Investimento investimento) {
        log.info("retirando_dinheiro");
        var entity = mapper.domainToEntity(investimento);
        dynamoDBMapper.save(entity);
        return entity.getCodigoCliente();
    }

    public BigDecimal saldoMensal(String mesSaldo) {
        log.info("saldo_mensal");
        return BigDecimal.ONE;
    }

    public void cancelarInvestimento(UUID identificacaoMovimentacao) {
        log.info("cancelando_investimento");
    }

}
