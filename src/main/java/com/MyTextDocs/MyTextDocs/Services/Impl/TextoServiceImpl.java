package com.MyTextDocs.MyTextDocs.Services.Impl;

import com.MyTextDocs.MyTextDocs.Models.Texto;
import com.MyTextDocs.MyTextDocs.Models.Usuario;
import com.MyTextDocs.MyTextDocs.Repository.TextoRepository;
import com.MyTextDocs.MyTextDocs.Repository.UsuarioRepository;
import com.MyTextDocs.MyTextDocs.Services.TextoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
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

        return textoRepository.findById(id);
    }


    @Override
    public Optional<List<Texto>> getAllTextoByUsuario(Long idUsuario) {

        return Optional.empty();
    }

    @Override
    public Boolean newTexto(Texto texto, Long idUsuario) {

        try{
            Usuario usuario = usuarioRepository.findById(idUsuario).get();
            texto.setUsuario(usuario);
            texto.setData(LocalDate.now());

            List<Texto> textos = usuario.getTextos();
            textos.add(textoRepository.save(texto));
            usuario.setTextos(textos);
            usuarioRepository.save(usuario);
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }

    @Override
    public Boolean editTexto(Texto texto) {

        try{
            texto.setUsuario( textoRepository.findById(texto.getId()).get().getUsuario());
            texto.setData(LocalDate.now());
            textoRepository.save(texto);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public Boolean deleteTexto(Long id) {
        return null;
    }
}
