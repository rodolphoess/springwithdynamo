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

    public UUID injetarDinheiro(InvestimentoRequest investimentoRequest) {
        //TODO: Considerar o valor movimentação positivo - DONE em Investimento
        //TODO: Somar o valor movimentação ao último saldo do cliente - Gerar um GET para recuperar esse dado
        //TODO: Gerar uma data e hora da movimentação - DONE em Investimento
        //TODO: Recuperar o mes da movimentação - DONE em Investimento

        log.info("injetando_dinheiro");
        var ultimoSaldo = repository.buscaUltimoSaldo(investimentoRequest.getCodigoCliente());
        var investimento = mapper.investimentoRequestToDomain(investimentoRequest)
                .valorMovimentadoEnquantoCredito(investimentoRequest.getValorMovimentado())
                .saldoClienteEnquantoCredito(ultimoSaldo, investimentoRequest.getValorMovimentado())
                .gerarDataHoraMovimentacao()
                .mesMovimentacao();
        return repository.injetarDinheiro(investimento);
    }

    public UUID retirarDinheiro(RetiradaRequest retiradaRequest) {
        //TODO: Considerar o valor da movimentação negativo - DONE em Investimento
        //TODO: Subtrair o valor movimentação ao último saldo do cliente - Gerar um GET para recuperar esse dado
        //TODO: Gerar uma data e hora da movimentação - DONE em Investimento
        //TODO: Recuperar o mês da movimentação - DONE em Investimento

        log.info("retirando_dinheiro");
        var ultimoSaldo = repository.buscaUltimoSaldo(retiradaRequest.getCodigoCliente());
        var retirada = mapper.retiradaRequestToDomain(retiradaRequest)
                .valorMovimentadoEnquantoDebito(retiradaRequest.getValorMovimentado())
                .saldoClienteEnquantoDebito(ultimoSaldo, retiradaRequest.getValorMovimentado())
                .gerarDataHoraMovimentacao()
                .mesMovimentacao();
        return repository.retirarDinheiro(retirada);
    }

    public BigDecimal saldoMensal(String mesSaldo) {
        log.info("saldo_mensal");
        return repository.saldoMensal(mesSaldo);
    }

    public void cancelarInvestimento(UUID identificacaoMovimentacao) {
        log.info("cancelando_investimento: {}", identificacaoMovimentacao);
        repository.cancelarInvestimento(identificacaoMovimentacao);
    }

}
