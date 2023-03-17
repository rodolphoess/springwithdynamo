package br.com.audacit.investimentos.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class Investimento {

    private UUID codigoMovimentacao;

    private UUID codigoCliente;

    private String mesMovimentacao;

    private LocalDateTime dataHoraMovimentacao;

    private BigDecimal valorMovimentado;

    private BigDecimal saldoCliente;

    public Investimento valorMovimentadoEnquantoCredito(BigDecimal valorMovimentado) {
        this.valorMovimentado = valorMovimentado;
        return this;
    }

    public Investimento valorMovimentadoEnquantoDebito(BigDecimal valorMovimentado) {
        this.valorMovimentado = valorMovimentado.negate();
        return this;
    }

    public Investimento gerarDataHoraMovimentacao() {
        this.dataHoraMovimentacao = LocalDateTime.now(ZoneId.of("Brasilia"));
        return this;
    }

    public Investimento mesMovimentacao() {
        this.mesMovimentacao = LocalDate.now().getMonth().name();
        return this;
    }

    public Investimento adicionaCreditoSaldoCliente(BigDecimal ultimoSaldo, BigDecimal valorMovimentado) {
        this.saldoCliente = ultimoSaldo.add(valorMovimentado);
        return this;
    }

    public Investimento debitaSaldoCliente(BigDecimal ultimoSaldo, BigDecimal valorMovimentado) {
        this.saldoCliente = ultimoSaldo.subtract(valorMovimentado);
        return this;
    }

    public BigDecimal filtraUltimoSaldo(List<Investimento> investimentos) {
        investimentos.forEach(investimento -> {
            if (investimento.getDataHoraMovimentacao().isAfter(this.dataHoraMovimentacao)) {
                this.dataHoraMovimentacao = investimento.getDataHoraMovimentacao();
                this.saldoCliente = investimento.getSaldoCliente();
            }
        });
        return this.saldoCliente;
    }

}
