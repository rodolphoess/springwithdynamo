package br.com.audacit.investimentos.service;

import br.com.audacit.investimentos.database.repository.InvestimentoRepository;
import br.com.audacit.investimentos.dto.in.InvestimentoRequest;
import br.com.audacit.investimentos.dto.in.RetiradaRequest;
import br.com.audacit.investimentos.mapper.InvestimentoMapper;
import br.com.audacit.investimentos.mapper.MapperFactory;
import br.com.audacit.investimentos.model.Investimento;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class InvestimentoService {

    private final InvestimentoRepository repository;
    private final InvestimentoMapper mapper = MapperFactory.criaInstanciaMapper(InvestimentoMapper.class);

    public UUID injetarDinheiro(InvestimentoRequest investimentoRequest) {
        log.info("injetando_dinheiro");
        var saldosCliente = repository.buscaSaldosCliente(investimentoRequest.getCodigoCliente());
        var investimento = mapper.investimentoRequestToDomain(investimentoRequest)
                .valorMovimentadoEnquantoCredito(investimentoRequest.getValorMovimentado())
                .gerarDataHoraMovimentacao()
                .mesMovimentacao();
        var ultimoSaldo = investimento.filtraUltimoSaldo(saldosCliente);
        investimento = investimento.adicionaCreditoSaldoCliente(ultimoSaldo, investimentoRequest.getValorMovimentado());
        return repository.realizarMovimentacao(investimento);
    }

    public UUID retirarDinheiro(RetiradaRequest retiradaRequest) {
        log.info("retirando_dinheiro");
        var saldosCliente = repository.buscaSaldosCliente(retiradaRequest.getCodigoCliente());
        var retirada = mapper.retiradaRequestToDomain(retiradaRequest)
                .valorMovimentadoEnquantoDebito(retiradaRequest.getValorMovimentado())
                .gerarDataHoraMovimentacao()
                .mesMovimentacao();
        var ultimoSaldo = retirada.filtraUltimoSaldo(saldosCliente);
        retirada = retirada.debitaSaldoCliente(ultimoSaldo, retiradaRequest.getValorMovimentado());
        return repository.realizarMovimentacao(retirada);
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
