package com.dw.futsabao.controller;

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
            List<Pagamento> pagamentos = null;

            if (ano == null && mes == null)
                pagamentos = pagamentoRepository.findAll();
            if (ano != null && mes == null)
                pagamentos = pagamentoRepository.findByAno(ano);
            if (ano == null && mes != null)
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            if(ano != null && mes != null)
                pagamentos = pagamentoRepository.findByMesAndAno(mes, ano);

            if (pagamentos.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(pagamentos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //GET por id
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
    }

    //POST novo pagamento
    @PostMapping
    public ResponseEntity<Pagamento> createPagamento(@RequestBody Pagamento pagamento) {
        try {
            Pagamento pagamentoResp = pagamentoRepository.save(new Pagamento(pagamento.getAno(), pagamento.getMes(), pagamento.getValor(), pagamento.getJogador()));
            return new ResponseEntity<>(pagamentoResp, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //PUT pagamento por id
    @PutMapping("/{id}")
    public ResponseEntity<Pagamento> updatePagamento(@PathVariable("id") Integer id, @RequestBody Pagamento pagamento)
    {
        try {
            Optional<Pagamento> data = pagamentoRepository.findById(id);

            if (data.isPresent())
            {
                Pagamento pagamentoResp = data.get();
                pagamentoResp.setAno(pagamento.getAno());
                pagamentoResp.setMes(pagamento.getMes());
                pagamentoResp.setValor(pagamento.getValor());
                pagamentoResp.setJogador(pagamento.getJogador());

                return new ResponseEntity<>(pagamentoRepository.save(pagamentoResp), HttpStatus.OK);
            }
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //PATCH pagamento por id
    @PatchMapping("/{id}")
    public ResponseEntity<Pagamento> parcialUpdatePagamento(@PathVariable("id") Integer id, @RequestBody Pagamento pagamento)
    {
        try
        {
            Optional<Pagamento> data = pagamentoRepository.findById(id);

            if(data.isEmpty())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            Pagamento pagamentoResp = data.get();

            if(pagamento.getAno() != null)
                pagamentoResp.setAno(pagamento.getAno());
            if(pagamento.getMes() != null)
                pagamentoResp.setMes(pagamento.getMes());
            if(pagamento.getValor() != null)
                pagamentoResp.setValor(pagamento.getValor());
            if(pagamento.getJogador() != null)
                pagamentoResp.setJogador(pagamento.getJogador());

            return new ResponseEntity<>(pagamentoRepository.save(pagamentoResp), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //DELETE por id
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePagamento(@PathVariable("id") Integer id){
        try {
            pagamentoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    //DELETE todos os pagamentos
    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllPagamento()
    {
        try {
            pagamentoRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}