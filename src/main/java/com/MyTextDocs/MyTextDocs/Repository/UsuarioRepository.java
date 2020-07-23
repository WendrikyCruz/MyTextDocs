package com.MyTextDocs.MyTextDocs.Repository;

import com.MyTextDocs.MyTextDocs.Models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
