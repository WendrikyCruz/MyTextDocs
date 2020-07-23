package com.MyTextDocs.MyTextDocs.Services.Impl;

import com.MyTextDocs.MyTextDocs.Models.Texto;
import com.MyTextDocs.MyTextDocs.Repository.TextoRepository;
import com.MyTextDocs.MyTextDocs.Repository.UsuarioRepository;
import com.MyTextDocs.MyTextDocs.Services.TextoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class TextoServiceImpl implements TextoService {

    @Autowired
    TextoRepository textoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public Optional<List<Texto>> getAllTexto() {
        return Optional.empty();
    }

    @Override
    public Optional<Texto> getTextoById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Texto>> getAllTextoByUsuario(Long idUsuario) {
        return Optional.empty();
    }

    @Override
    public Boolean newTexto(Texto texto, Long idUsuario) {
        return null;
    }

    @Override
    public Boolean editTexto(Texto texto) {
        return null;
    }

    @Override
    public Boolean deleteTexto(Long id) {
        return null;
    }
}
