package com.example.demo.service.Utils;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entities.Usuario;
import com.example.demo.dto.usuarioDTO;
import com.example.demo.mapper.Usuariomapper;
import com.example.demo.repository.IUsuarioRepositorio;


@Service
public class UsuarioService {
    @Autowired
    private IUsuarioRepositorio usuarioRepositorio;

    @Autowired
    private Usuariomapper usuariomapper;

    public Optional<usuarioDTO> busacarporId(Long id)
    {
        return usuarioRepositorio.findById(id).map(usuariomapper::toDto);
    }
    
}
