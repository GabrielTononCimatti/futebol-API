package com.dw.futsabao.repository;

import com.dw.futsabao.model.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JogadorRepository extends JpaRepository<Jogador, Integer>, JpaSpecificationExecutor<Jogador> {
    List<Jogador> findByNomeContainingIgnoreCase(String nome);
    List<Jogador> findByEmailContainingIgnoreCase(String email);
    List<Jogador> findByNomeContainingIgnoreCaseAndEmailContainingIgnoreCase(String nome, String email);
}
