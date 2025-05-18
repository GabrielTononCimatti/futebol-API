package com.dw.futsabao.controller;

import com.dw.futsabao.model.Jogador;
import com.dw.futsabao.repository.JogadorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jogadores")
public class JogadorController {

    @Autowired
    JogadorRepository jogadorRepository;

    //GET com filtros (nome e email)
    @GetMapping
    public ResponseEntity<List<Jogador>> buscarJogadores(@RequestParam(required = false) String nome, @RequestParam(required = false) String email) {
        try {
            List<Jogador> jogadores;

            if (nome == null && email == null)
                jogadores = jogadorRepository.findAll();
            else if (nome != null && email == null)
                jogadores = jogadorRepository.findByNomeContainingIgnoreCase(nome);
            else if (nome == null && email != null)
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

    //GET por id
    @GetMapping("/{id}")
    public ResponseEntity<Jogador> buscarPorId(@PathVariable("id") Integer id) {
        try {
            Optional<Jogador> jogador = jogadorRepository.findById(id);

            if (jogador.isEmpty())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(jogador.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //POST novo jogador
    @PostMapping
    public ResponseEntity<Jogador> createJogador(@RequestBody Jogador jogador) {
        try {
            Jogador jogadorResp = jogadorRepository.save(new Jogador(jogador.getNome(), jogador.getEmail(), jogador.getDatanasc(), jogador.getPagamentos()));
            return new ResponseEntity<>(jogadorResp, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //PUT jogador por id
    @PutMapping("/{id}")
    public ResponseEntity<Jogador> updateJogador(@PathVariable("id") Integer id, @RequestBody Jogador jogador)
    {
        Optional<Jogador> data = jogadorRepository.findById(id);

        if (data.isPresent())
        {
            Jogador jogadorResp = data.get();
            jogadorResp.setNome(jogador.getNome());
            jogadorResp.setEmail(jogador.getEmail());
            jogadorResp.setDatanasc(jogador.getDatanasc());
            jogadorResp.setPagamentos(jogador.getPagamentos());

            return new ResponseEntity<>(jogadorRepository.save(jogadorResp), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    //DELETE por id
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteJogador(@PathVariable("id") Integer id){
        try {
            jogadorRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    //DELETE todos os jogadores
    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllJogador()
    {
        try {
            jogadorRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

