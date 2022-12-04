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
@RequestMapping("/investimento")
public class InvestimentoController {

    private InvestimentoService investimentoService;

    @PostMapping("/investir")
    public ResponseEntity<Object> investir(@Valid @RequestBody InvestimentoRequest investimento) {
        return ResponseEntity.status(HttpStatus.CREATED).body(Object.class);
    }

    @PostMapping("/retirar")
    public ResponseEntity<Object> retirar(@Valid @RequestBody RetiradaRequest retirada) {
        return ResponseEntity.status(HttpStatus.CREATED).body(Object.class);
    }

    @GetMapping("/saldo/mensal")
    public ResponseEntity<Object> saldoMensal(@RequestParam("mes") String mes) {
        return ResponseEntity.status(HttpStatus.OK).body(Object.class);
    }

    @DeleteMapping("/cancelar/movimentacao")
    public ResponseEntity<Object> cancelarMovimentacao(@RequestParam("movimentacao") UUID identificacaoMovimentacao) {
        return ResponseEntity.status(HttpStatus.OK).body("Movimentação cancelada com sucesso!");
    }

}
