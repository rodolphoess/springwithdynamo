package br.com.audacit.investimentos.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

}
