package br.com.audacit.investimentos.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
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

}
