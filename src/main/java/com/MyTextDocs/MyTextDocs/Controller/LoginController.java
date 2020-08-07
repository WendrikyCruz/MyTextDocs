package com.MyTextDocs.MyTextDocs.Controller;

import com.MyTextDocs.MyTextDocs.Models.Usuario;
import com.MyTextDocs.MyTextDocs.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/Login")
    public String loginIndex(){
        return "Login";
    }

    @PostMapping("/Login")
    public String efetuaLogin(String email, String senha, HttpSession session){
        Usuario user = usuarioService.verificaUsuario(email, senha).get();
        if(user != null){
            //ModelAndView mv = new ModelAndView("MeusTextos");
            session.setAttribute("usuarioLogado", user);
            return "/MeusTextos";
        }

        return "/Login";
    }
}
