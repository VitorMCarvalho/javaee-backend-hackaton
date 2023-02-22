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
public class UserCommonRequestDTO {

    private Long id;
    public String nome;

    public String login;

    public String email;

    public LocalDate dataNascimento;

    public LocalDateTime dataAtualizacao;

    public UserCommonRequestDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.login = usuario.getLogin();
        this.email = usuario.getEmail();
        this.dataNascimento = usuario.getDataNascimento();
        this.dataAtualizacao = usuario.getDataAtualizacao();
    }

    public UserCommonRequestDTO(Long id, String nome, String login,String email, LocalDate dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.dataAtualizacao = LocalDateTime.now();
    }
}
