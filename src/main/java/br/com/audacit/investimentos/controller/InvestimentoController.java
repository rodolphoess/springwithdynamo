package br.com.audacit.investimentos.controller;

import br.com.audacit.investimentos.dto.in.InvestimentoRequest;
import br.com.audacit.investimentos.dto.in.RetiradaRequest;
import br.com.audacit.investimentos.service.InvestimentoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/investimento")
public class InvestimentoController {

    private final InvestimentoService investimentoService;

    @PostMapping("/investir")
    public ResponseEntity<Object> investir(@Valid @RequestBody InvestimentoRequest investimento) {
        log.info("solicitacao_investimento_recebida={}", investimento.toString());
        var identificacaoInvestimento = investimentoService.injetarDinheiro(investimento);
        log.info("solicitacao_investimento_concluida_com_sucesso={}", identificacaoInvestimento);
        return ResponseEntity.status(HttpStatus.CREATED).body(identificacaoInvestimento);
    }

    @PostMapping("/retirar")
    public ResponseEntity<Object> retirar(@Valid @RequestBody RetiradaRequest retirada) {
        log.info("solicitacao_retirada_recebida={}", retirada.toString());
        var identificacaoRetirada = investimentoService.retirarDinheiro(retirada);
        log.info("solicitacao_retirada_concluida_com_sucesso={}", identificacaoRetirada);
        return ResponseEntity.status(HttpStatus.CREATED).body(identificacaoRetirada);
    }

    @GetMapping("/saldo/mensal")
    public ResponseEntity<Object> saldoMensal(@RequestParam("mes") String mes) {
        log.info("solicitacao_recuperacao_saldo_mensal={}", mes);
        var saldoMes = investimentoService.saldoMensal(mes);
        log.info("saldo_mensal_recuperado_com_sucesso=R$ {}", saldoMes);
        return ResponseEntity.status(HttpStatus.OK).body(saldoMes);
    }

    @DeleteMapping("/cancelar")
    public ResponseEntity<Object> cancelarMovimentacao(@RequestParam("movimentacao") UUID identificacaoMovimentacao) {
        log.info("solicitacao_cancelamento_movimentacao_recebida={}", identificacaoMovimentacao);
        investimentoService.cancelarInvestimento(identificacaoMovimentacao);
        log.info("cancelamento_movimentacao_realizado_com_sucesso");
        return ResponseEntity.status(HttpStatus.OK).body("Movimentação cancelada com sucesso!");
    }

}
