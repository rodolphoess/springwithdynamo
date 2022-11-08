package br.com.audacit.investimentos.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Investimento {

    private UUID codigoInvestimento;

    private BigDecimal valorInvestido;

    private BigDecimal saldoTotal;

    private LocalDate mesMovimentacao;

}
