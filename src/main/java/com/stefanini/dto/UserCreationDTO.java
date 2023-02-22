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
    public String nome;

    public String login;

    public String senha;

    public String email;

    public LocalDate dataNascimento;

    public LocalDateTime dataCriacao;

    public LocalDateTime dataAtualizacao;

    public UserCreationDTO(Usuario user){
        this.nome = user.getNome();
        this.login = user.getLogin();
        this.senha = user.getSenha();
        this.email = user.getEmail();
        this.dataNascimento = user.getDataNascimento();
        this.dataCriacao = LocalDateTime.now();
        this.dataAtualizacao = LocalDateTime.now();
    }
}
