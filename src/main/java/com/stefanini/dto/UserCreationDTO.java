package com.stefanini.dto;

import com.stefanini.models.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCreationDTO {

    public Long id;
    public String nome;

    public String login;

    public String email;

    public String senha;

    public LocalDate dataNascimento;

    public UserCreationDTO(Usuario user){
        this.id = user.getId();
        this.nome = user.getNome();
        this.login = user.getLogin();
        this.senha = user.getSenha();
        this.email = user.getEmail();
        this.dataNascimento = user.getDataNascimento();
    }
}
