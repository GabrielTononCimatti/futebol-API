package com.dw.futsabao.controller;

import com.dw.futsabao.model.Jogador;
import com.dw.futsabao.model.Pagamento;
import com.dw.futsabao.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{id}")
    public ResponseEntity<Pagamento> buscarPorId(@PathVariable("id") Integer id) {
        try {
            Optional<Pagamento> pagamento = pagamentoRepository.findById(id);

            if (pagamento.isEmpty())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(pagamento.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    @PostMapping
    public Pagamento criar(@RequestBody Pagamento pagamento) {
        return pagamentoService.salvar(pagamento);
    }
}