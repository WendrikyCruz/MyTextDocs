package com.MyTextDocs.MyTextDocs.Services.Impl;

import com.MyTextDocs.MyTextDocs.Models.Texto;
import com.MyTextDocs.MyTextDocs.Models.Usuario;
import com.MyTextDocs.MyTextDocs.Repository.TextoRepository;
import com.MyTextDocs.MyTextDocs.Repository.UsuarioRepository;
import com.MyTextDocs.MyTextDocs.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

        try{
            usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
            usuarioRepository.save(usuario);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public Boolean editUsuario(Usuario usuario) {
        return null;
    }

    @Override
    public Boolean deleteUsuario(Long id) {
        return null;
    }

    @Override
    public  Optional<Usuario> verificaUsuario(String user, String senha) {

        List<Usuario> users = usuarioRepository.findAll();
        for ( Usuario u : users) {
            if(u.getEmail().equalsIgnoreCase(user) && u.getSenha().equalsIgnoreCase(senha)){
                return Optional.of(u);
            }
        }
        return Optional.empty();
    }

    @Override
    public  Optional<Usuario> getUserByUsername(String user) {
        List<Usuario> users = usuarioRepository.findAll();
        for ( Usuario u : users) {

            if(u.getEmail().equalsIgnoreCase(user)){
                return Optional.of(u);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean removeTextoUsuario(long idUsuario, long idTexto){

        try{
            Usuario usuario = usuarioRepository.findById(idUsuario).get();
            for(Texto texto : usuario.getTextos()){
                if(texto.getId() == idTexto){
                    usuario.getTextos().remove(texto);
                    break;
                }}
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
