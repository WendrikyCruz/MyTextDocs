package com.MyTextDocs.MyTextDocs.Services.Impl;

import com.MyTextDocs.MyTextDocs.Models.Usuario;
import com.MyTextDocs.MyTextDocs.Repository.TextoRepository;
import com.MyTextDocs.MyTextDocs.Repository.UsuarioRepository;
import com.MyTextDocs.MyTextDocs.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public Optional<List<Usuario>> getAllUsuario() {
        return Optional.empty();
    }

    @Override
    public Optional<Usuario> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Boolean newUsuario(Usuario usuario) {
        return null;
    }

    @Override
    public Boolean editUsuario(Usuario usuario) {
        return null;
    }

    @Override
    public Boolean deleteUsuario(Long id) {
        return null;
    }
}
