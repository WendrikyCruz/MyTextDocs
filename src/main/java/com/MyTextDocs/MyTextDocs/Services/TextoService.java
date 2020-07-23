package com.MyTextDocs.MyTextDocs.Services;

import com.MyTextDocs.MyTextDocs.Models.Texto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TextoService {
    Optional<List<Texto>> getAllTexto();
    Optional<Texto> getTextoById(Long id);
    Optional<List<Texto>> getAllTextoByUsuario(Long idUsuario);
    Boolean newTexto(Texto texto, Long idUsuario);
    Boolean editTexto(Texto texto);
    Boolean deleteTexto(Long id);
}
