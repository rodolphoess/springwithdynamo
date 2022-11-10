package br.com.audacit.investimentos.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class SaldoMensalResponse {

    @Positive
    private BigDecimal saldo;

    private LocalDate mes;

}
