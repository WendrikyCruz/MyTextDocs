package com.MyTextDocs.MyTextDocs.Controller;

import com.MyTextDocs.MyTextDocs.Models.UserPrincipal;
import com.MyTextDocs.MyTextDocs.Models.Usuario;
import com.MyTextDocs.MyTextDocs.Services.UserDetailsService;
import com.MyTextDocs.MyTextDocs.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class LoginController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    UserDetailsService userDetailsService;

    @GetMapping("/Login")
    public String loginIndex(){

        return "Login"; //new ModelAndView("Login");
    }

    @PostMapping("/Login")
    public ModelAndView Login(String username, String password) throws IOException {
          ModelAndView mv = new ModelAndView();
          Usuario user = usuarioService.verificaUsuario(username, password).get();
          mv.setViewName("/MeusTextos");
          mv.addObject("usuario", user);
          return mv;
    }


    @GetMapping("/Logout")
    public String logout(){
        return "Logout";
    }


}
