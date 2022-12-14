package br.com.audacit.investimentos.service;

import br.com.audacit.investimentos.dto.in.InvestimentoRequest;
import br.com.audacit.investimentos.dto.in.RetiradaRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Slf4j
@Service
public class InvestimentoService {

    public UUID injetarDinheiro(InvestimentoRequest investimento) {
        log.info("injetando_dinheiro");
        return UUID.randomUUID();
    }

    public UUID retirarDinheiro(RetiradaRequest retirada) {
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
