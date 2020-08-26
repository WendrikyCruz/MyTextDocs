package com.MyTextDocs.MyTextDocs.Controller;

import com.MyTextDocs.MyTextDocs.Models.Usuario;
import com.MyTextDocs.MyTextDocs.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class NovoUsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/NovoUsuario")
    public String NovoUsuario(Usuario usuario, RedirectAttributes redirectAttributes){
       if( usuarioService.newUsuario(usuario)){
           redirectAttributes.addFlashAttribute("mensagemCadastroSucesso", "Cadastro concluído com sucesso!.");
           return "/Login";
       }
        redirectAttributes.addFlashAttribute("mensagemCadastroErro", "Não Foi Possivel Concluir o cadastro.");
        return "/Login";
    }
}
