package br.com.audacit.investimentos.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class RetiradaRequest {

    private UUID codigoInvestimento;

    @Positive
    private BigDecimal valor;

}
