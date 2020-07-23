package com.MyTextDocs.MyTextDocs.Models;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import javax.persistence.Id;
import javax.xml.soap.Text;
import java.util.List;

@Entity
@Table(name="tb_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long Id;
    @Getter @Setter
    private String Nome;
    @Getter @Setter
    private String Email;
    @Getter @Setter
    private String Senha;
    @Getter @Setter
    @OneToMany
    private List<Texto> Textos;



}
