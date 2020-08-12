package com.MyTextDocs.MyTextDocs.Controller;

import com.MyTextDocs.MyTextDocs.Models.Usuario;
import com.MyTextDocs.MyTextDocs.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class LoginController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/Login")
    public String loginIndex(){
        return "Login";
    }

    @PostMapping("/Login")
    public ModelAndView efetuaLogin(String email, String senha, HttpSession session, HttpServletResponse response, HttpServletRequest request) throws IOException {
        ModelAndView mv = new ModelAndView();
        Usuario user = usuarioService.verificaUsuario(email, senha).get();
        if(user != null){
            mv.setViewName("/MeusTextos");
            mv.addObject("usuario", user);
            //session = request.getSession();
            session.setAttribute("usuarioLogado", user);
            return mv;
        }else{
            //((HttpServletResponse) response).sendRedirect("Login");
            mv.setViewName("/Login");
            return mv;
        }

    }
}
