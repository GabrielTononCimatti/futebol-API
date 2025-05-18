package com.dw.futsabao.controller;

import com.dw.futsabao.model.Jogador;
import com.dw.futsabao.model.Pagamento;
import com.dw.futsabao.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    PagamentoRepository pagamentoRepository;

    //GET com filtros (nome e email)
    @GetMapping
    public ResponseEntity<List<Pagamento>> buscarPagamentos(@RequestParam(required = false) Short ano, @RequestParam(required = false) Byte mes, @RequestParam(required = false) BigDecimal valor) {
        try {
            List<Jogador> jogadores;

            if (nome == null && email == null)
                jogadores = jogadorRepository.findAll();
            else if (nome != null && email == null)
                jogadores = jogadorRepository.findByNomeContainingIgnoreCase(nome);
            else if (nome != null && email == null)
                jogadores = jogadorRepository.findByEmailContainingIgnoreCase(email);
            else
                jogadores = jogadorRepository.findByNomeContainingIgnoreCaseAndEmailContainingIgnoreCase(nome, email);

            if (jogadores.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(jogadores, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @GetMapping
    public List<Pagamento> listar() {
        return pagamentoService.listarTodos();
    }

    @GetMapping("/jogador/{codJogador}")
    public List<Pagamento> listarPorJogador(@PathVariable Integer codJogador) {
        return pagamentoService.listarPorJogador(codJogador);
    }

    @PostMapping
    public Pagamento criar(@RequestBody Pagamento pagamento) {
        return pagamentoService.salvar(pagamento);
    }
}