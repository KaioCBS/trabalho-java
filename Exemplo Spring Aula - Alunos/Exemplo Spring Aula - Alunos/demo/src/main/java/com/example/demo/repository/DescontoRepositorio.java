package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.Entities.Desconto;

@Repository
public interface DescontoRepositorio extends JpaRepository<Desconto, Long> {
    List<Desconto> findByCotacaoId(Long cotacaoId);
}
