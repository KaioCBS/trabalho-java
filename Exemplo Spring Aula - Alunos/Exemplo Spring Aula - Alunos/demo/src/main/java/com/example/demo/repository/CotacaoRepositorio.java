package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.Entities.Cotacao;

@Repository
public interface CotacaoRepositorio extends JpaRepository<Cotacao, Long> {

}