package com.MyTextDocs.MyTextDocs.Controller;


import com.MyTextDocs.MyTextDocs.Models.Texto;
import com.MyTextDocs.MyTextDocs.Models.Usuario;
import com.MyTextDocs.MyTextDocs.Services.TextoService;
import com.MyTextDocs.MyTextDocs.Services.UserDetailsService;
import com.MyTextDocs.MyTextDocs.Services.UsuarioService;


import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;

import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;



@Controller
public class MeusTextosController {


    @Autowired
    UsuarioService usuarioService;

    @Autowired
    TextoService textoService;

    @Autowired
    UserDetailsService userDetailsService;

    @GetMapping("/MeusTextos")
    public ModelAndView getMeuTextos() throws Exception {

        String userName = ((UserDetails)userDetailsService.getLoggedInUser()).getUsername();
        String password = ((UserDetails)userDetailsService.getLoggedInUser()).getPassword();
        ModelAndView mv = new ModelAndView("MeusTextos");
        Usuario userView = usuarioService.verificaUsuario(userName, password).get();
        mv.addObject(userView);
        return mv;
    }

    @GetMapping("/MeusTextos/{id}")
    public ModelAndView getTexto(@PathVariable long id )
    {
                Texto texto = textoService.getTextoById(id).get();
                ModelAndView mv = new ModelAndView("VisualizarTexto");
                mv.addObject(texto);
                return mv;
    }

    @GetMapping("/Editar/{id}")
    public ModelAndView Editar(@PathVariable long id)
    {
        Texto texto = textoService.getTextoById(id).get();
        ModelAndView mv = new ModelAndView("EditaTexto");
        mv.addObject(texto);
        return mv;
    }

    @PostMapping("/Editar/EditarTexto")
    public String EditarTexto(Texto texto, RedirectAttributes redirectAttributes)
    {
        try{
            if(textoService.editTexto(texto)){
                redirectAttributes.addFlashAttribute("mensagemEditado", "Texto Editado com Sucesso!");
                return "redirect:/MeusTextos";
            }
            else{
                redirectAttributes.addFlashAttribute("mensagem", "Algo deu errado");
                return "redirect:/NovoTexto";
            }
        }catch(Exception e){
            return "redirect:/NovoTexto";
        }
    }

    @GetMapping("/NovoTexto")
    public String getNovoTexto(){
        return "NovoTexto";
    }

    @PostMapping("/NovoTexto")
    public String newTexto(Texto texto, RedirectAttributes redirectAttributes){
        String userName = ((UserDetails)userDetailsService.getLoggedInUser()).getUsername();
        String password = ((UserDetails)userDetailsService.getLoggedInUser()).getPassword();
        Usuario usuario = usuarioService.verificaUsuario(userName, password).get();
        if(textoService.newTexto(texto, usuario.getId())){
            return "redirect:/MeusTextos";
        }
        else{
            redirectAttributes.addFlashAttribute("mensagem", "Algo deu errado");
            return "redirect:/NovoTexto";
        }
    }

    @GetMapping("/Download/{id}")
    public HttpEntity<byte[]> downloadPdf(@PathVariable Long id) throws IOException {
        if ( textoService.getTextoById(id).isPresent()) {
            Texto texto = textoService.getTextoById(id).get();
            File file = new File(texto.getNome());
            HtmlConverter.convertToPdf(texto.getTexto(),new PdfWriter(file.getName()));
            byte[] arquivo = Files.readAllBytes( Paths.get(file.getPath()) );
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Content-Disposition", "attachment;filename=\""+texto.getNome()+".pdf\"");
            HttpEntity<byte[]> entity = new HttpEntity<byte[]>( arquivo, httpHeaders);
            file.delete();
            return entity;
        }
        return null;
    }

    @GetMapping("/MeusTextos/deletar/{idTexto}")
    public String deletar(@PathVariable Long idTexto, RedirectAttributes redirectAttributes) throws Exception {

        String userName = ((UserDetails)userDetailsService.getLoggedInUser()).getUsername();
        String password = ((UserDetails)userDetailsService.getLoggedInUser()).getPassword();
        ModelAndView mv = new ModelAndView("MeusTextos");
        Usuario user = usuarioService.verificaUsuario(userName, password).get();
        if(usuarioService.removeTextoUsuario(user.getId(), idTexto)){
            if(textoService.deleteTexto( (long)idTexto)){
                redirectAttributes.addFlashAttribute("mensagemDeletadoSucesso", "Documento Deletado com Sucesso!");
                return "redirect:/MeusTextos";
            }else{
                redirectAttributes.addFlashAttribute("mensagemDeletadoErro", "Não Foi Possivel Deletar Documento. Problema Deletar Texto.");
                return "redirect:/MeusTextos";
            }
        }
        redirectAttributes.addFlashAttribute("mensagemDeletadoErro", "Não Foi Possivel Deletar Documento. Problema em Desvincular Texto do Usuario.");
        return "redirect:/MeusTextos";
    }


}
