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

    //GET com filtros (ano e mes/ano)
    @GetMapping
    public ResponseEntity<List<Pagamento>> buscarPagamentos(@RequestParam(required = false) Short ano, @RequestParam(required = false) Byte mes) {
        try {
            List<Pagamento> pagamentos;

            if (ano == null && mes == null)
                pagamentos = pagamentoRepository.findAll();
            else if (ano != null && mes == null)
                pagamentos = pagamentoRepository.findByAno(ano);
            else if (ano == null && mes != null)
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            else
                pagamentos = pagamentoRepository.findByMesAndAno(mes, ano);

            if (pagamentos.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(pagamentos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
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