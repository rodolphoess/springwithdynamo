package br.com.audacit.investimentos.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Slf4j
@Service
public class InvestimentoService {

    public UUID injetarDinheiro() {
        log.info("injetando_dinheiro");
        return UUID.randomUUID();
    }

    public UUID retirarDinheiro() {
        log.info("retirando_dinheiro");
        return UUID.randomUUID();
    }

    public BigDecimal saldoMensal() {
        log.info("saldo_mensal");
        return BigDecimal.ONE;
    }

    public UUID cancelarInvestimento() {
        log.info("cancelando_investimento");
        return UUID.randomUUID();
    }

}
