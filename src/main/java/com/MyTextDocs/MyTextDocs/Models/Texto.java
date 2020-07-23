package com.MyTextDocs.MyTextDocs.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tb_texto")
public class Texto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long Id;

    @Getter @Setter
    private String Nome;

    @Getter @Setter
    @Lob
    private String Texto;

    @Getter @Setter
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate data;

    @Getter @Setter
    @ManyToOne
    private Usuario usuario;


}
