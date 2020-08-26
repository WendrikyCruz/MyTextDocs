package com.MyTextDocs.MyTextDocs.Services;

import com.MyTextDocs.MyTextDocs.Models.UserPrincipal;
import com.MyTextDocs.MyTextDocs.Models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    UsuarioService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = service.getUserByUsername(username).get();
        if(usuario == null){
            throw new UsernameNotFoundException("Usuario não encontrado");
        }

        return new UserPrincipal(usuario);
    }

    public Object getLoggedInUser(){
        Object auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return auth;
    }
}
