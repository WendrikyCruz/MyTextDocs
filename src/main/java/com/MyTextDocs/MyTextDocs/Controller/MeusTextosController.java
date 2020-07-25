package com.MyTextDocs.MyTextDocs.Controller;

import com.MyTextDocs.MyTextDocs.Models.Texto;
import com.MyTextDocs.MyTextDocs.Models.Usuario;
import com.MyTextDocs.MyTextDocs.Services.TextoService;
import com.MyTextDocs.MyTextDocs.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
public class MeusTextosController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    TextoService textoService;

    @GetMapping("/MeusTextos")
    public ModelAndView getMeuTextos()
    {
        ModelAndView mv = new ModelAndView("MeusTextos");
        Long id = Long.valueOf(5);
        Usuario usuario = usuarioService.getUsuarioById(id).get();
        mv.addObject(usuario);
        return mv;

    }

    @GetMapping("/MeusTextos/{id}")
    public ModelAndView getTexto(@PathVariable long id)
    {
        Texto texto = textoService.getTextoById(id).get();
        ModelAndView mv = new ModelAndView("VisualizarTexto");
        mv.addObject(texto);
        return mv;
    }

    @GetMapping("/{id}")
    public ModelAndView Editar(@PathVariable long id)
    {
        Texto texto = textoService.getTextoById(id).get();
        ModelAndView mv = new ModelAndView("EditaTexto");
        mv.addObject(texto);
        return mv;
    }

    @GetMapping("/NovoTexto")
    public String getNovoTexto(){
        return "/NovoTexto";
    }

    @PostMapping("/NovoTexto")
    public String newTexto(Texto texto, RedirectAttributes redirectAttributes){
        Long id = Long.valueOf(5);
        if(textoService.newTexto(texto, id)){
            return "redirect:/MeusTextos";
        }
        else{
            redirectAttributes.addFlashAttribute("mensagem", "Algo deu errado");
            return "redirect:/NovoTexto";
        }

    }


}
