package br.com.audacit.investimentos.service;

import br.com.audacit.investimentos.database.entity.InvestimentoEntity;
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
        var ultimoSaldo = filtraUltimoSaldoCliente(saldosCliente);
        var investimento = mapper.investimentoRequestToDomain(investimentoRequest)
                .valorMovimentadoEnquantoCredito(investimentoRequest.getValorMovimentado())
                .saldoClienteEnquantoCredito(ultimoSaldo, investimentoRequest.getValorMovimentado())
                .gerarDataHoraMovimentacao()
                .mesMovimentacao();
        return repository.realizarMovimentacao(investimento);
    }

    private BigDecimal filtraUltimoSaldoCliente(List<Investimento> saldosCliente) {
        //TODO: Ver como percorrer toda a lista e achar a última entrada baseada na dataHoraMovimentacao
        return BigDecimal.ZERO;
    }

    public UUID retirarDinheiro(RetiradaRequest retiradaRequest) {
        log.info("retirando_dinheiro");
        var saldosCliente = repository.buscaSaldosCliente(retiradaRequest.getCodigoCliente());
        var ultimoSaldo = filtraUltimoSaldoCliente(saldosCliente);
        var retirada = mapper.retiradaRequestToDomain(retiradaRequest)
                .valorMovimentadoEnquantoDebito(retiradaRequest.getValorMovimentado())
                .saldoClienteEnquantoDebito(ultimoSaldo, retiradaRequest.getValorMovimentado())
                .gerarDataHoraMovimentacao()
                .mesMovimentacao();
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
