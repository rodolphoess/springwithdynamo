package br.com.audacit.investimentos.service;

import br.com.audacit.investimentos.database.repository.InvestimentoRepository;
import br.com.audacit.investimentos.dto.in.InvestimentoRequest;
import br.com.audacit.investimentos.dto.in.RetiradaRequest;
import br.com.audacit.investimentos.mapper.InvestimentoMapper;
import br.com.audacit.investimentos.mapper.MapperFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class InvestimentoService {

    private final InvestimentoRepository repository;
    private final InvestimentoMapper mapper = MapperFactory.criaInstanciaMapper(InvestimentoMapper.class);

    public UUID injetarDinheiro(InvestimentoRequest investimento) {
        log.info("injetando_dinheiro");
        //TODO: Considerar o valor movimentação positivo
        //TODO: Somar o valor movimentação ao último saldo do cliente
        //TODO: Gerar uma data e hora da movimentação
        //TODO: Recuperar o mes da movimentação
        return repository.injetarDinheiro(mapper.investimentoRequestToDomain(investimento));
    }

    public UUID retirarDinheiro(RetiradaRequest retirada) {
        log.info("retirando_dinheiro");
        //TODO: Considerar o valor da movimentação negativo
        //TODO: Subtrair o valor movimentação ao último saldo do cliente
        //TODO: Gerar uma data e hora da movimentação
        //TODO: Recuperar o mês da movimentação
        return repository.retirarDinheiro(mapper.retiradaRequestToDomain(retirada));
    }

    public BigDecimal saldoMensal(String mesSaldo) {
        log.info("saldo_mensal");
        return repository.saldoMensal(mesSaldo);
    }

    public void cancelarInvestimento(UUID identificacaoMovimentacao) {
        log.info("cancelando_investimento");
        repository.cancelarInvestimento(identificacaoMovimentacao);
    }

}
