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

    @GetMapping("/")
    public String loginIndex(){
        return "Login";
    }

    @PostMapping("/")
    public ModelAndView efetuaLogin(String email, String senha, HttpSession session){
        ModelAndView mv = new ModelAndView();
        System.out.println("Senha "+ senha + "email "+ email);
        Usuario user = usuarioService.verificaUsuario(email, senha).get();
        System.out.println("Nome " + user.getNome());
        if(user != null){
            System.out.println("Entrei no if");
            mv.setViewName("MeusTextos");
            mv.addObject("usuario", user);
            session.setAttribute("usuarioLogado", user);
            return mv;
        }
        mv.setViewName("/");
        return mv;
    }
}
