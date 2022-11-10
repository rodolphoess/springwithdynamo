package br.com.audacit.investimentos.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class Investimento {

    private UUID codigoInvestimento;

    private BigDecimal valorInvestido;

    private BigDecimal saldoTotal;

    private BigDecimal saldoMensal;

    private LocalDate mesMovimentacao;

}
