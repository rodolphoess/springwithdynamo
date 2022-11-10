package br.com.audacit.investimentos.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class InvestimentoRequest {

    @Positive
    private BigDecimal valorInvestido;

}
