package com.MyTextDocs.MyTextDocs.Models;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GeneratorType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.persistence.Id;
import javax.xml.soap.Text;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="tb_usuario")
public class Usuario implements UserDetails {

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


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String senha) {
        Senha = senha;
    }

    public List<Texto> getTextos() {
        return Textos;
    }

    public void setTextos(List<Texto> textos) {
        Textos = textos;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.getSenha();
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
