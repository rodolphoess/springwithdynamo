package br.com.audacit.investimentos.database.repository;

import br.com.audacit.investimentos.database.entity.InvestimentoEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.UUID;

@Slf4j
@Repository
@RequiredArgsConstructor
public class InvestimentoRepository {

    public UUID injetarDinheiro(InvestimentoEntity entity) {
        log.info("injetando_dinheiro");
        return UUID.randomUUID();
    }

    public UUID retirarDinheiro(InvestimentoEntity entity) {
        log.info("retirando_dinheiro");
        return UUID.randomUUID();
    }

    public BigDecimal saldoMensal(String mesSaldo) {
        log.info("saldo_mensal");
        return BigDecimal.ONE;
    }

    public void cancelarInvestimento(UUID identificacaoMovimentacao) {
        log.info("cancelando_investimento");
    }

}
