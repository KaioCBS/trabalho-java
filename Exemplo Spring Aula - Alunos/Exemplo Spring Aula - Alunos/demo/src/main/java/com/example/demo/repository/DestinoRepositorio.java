package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.Entities.Destino;

@Repository
public interface DestinoRepositorio extends JpaRepository<Destino, Long> {
}