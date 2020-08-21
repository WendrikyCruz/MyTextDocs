package com.MyTextDocs.MyTextDocs.Services;

import com.MyTextDocs.MyTextDocs.Models.Usuario;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


public interface UsuarioService {

    Optional<List<Usuario>> getAllUsuario();
    Optional<Usuario> getUsuarioById(Long id);
    Boolean newUsuario(Usuario usuario);
    Boolean editUsuario(Usuario usuario);
    Boolean deleteUsuario(Long id);
    Optional<Usuario> verificaUsuario(String user, String senha);
    Optional<Usuario> getUserByUsername(String user);
    boolean removeTextoUsuario(long idUsuario, long idTexto);
}
