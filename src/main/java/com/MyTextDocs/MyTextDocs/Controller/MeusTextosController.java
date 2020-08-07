package com.MyTextDocs.MyTextDocs.Controller;

import com.MyTextDocs.MyTextDocs.Models.Texto;
import com.MyTextDocs.MyTextDocs.Models.Usuario;
import com.MyTextDocs.MyTextDocs.Services.TextoService;
import com.MyTextDocs.MyTextDocs.Services.UsuarioService;


import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;


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

    @GetMapping("/Editar/{id}")
    public ModelAndView Editar(@PathVariable long id)
    {
        Texto texto = textoService.getTextoById(id).get();
        ModelAndView mv = new ModelAndView("EditaTexto");
        mv.addObject(texto);
        return mv;
    }

    @PostMapping("/Editar/{id}")
    public String EditarTexto(Texto texto, RedirectAttributes redirectAttributes)
    {
        Long id = Long.valueOf(5);
        if(textoService.editTexto(texto)){
            redirectAttributes.addFlashAttribute("mensagemEditado", "Texto Editado com Sucesso!");
            return "redirect:/MeusTextos";
        }
        else{
            redirectAttributes.addFlashAttribute("mensagem", "Algo deu errado");
            return "redirect:/NovoTexto";
        }
    }

    @GetMapping("/NovoTexto")
    public String getNovoTexto(){
        return "/NovoTexto";
    }

    @PostMapping("/NovoTexto")
    public String newTexto(Texto texto, RedirectAttributes redirectAttributes){
        Long id = Long.valueOf(5);
        //texto.setNome(texto.getNome()+".pdf");
        if(textoService.newTexto(texto, id)){
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

            return entity;

        }

        return null;

    }


}
