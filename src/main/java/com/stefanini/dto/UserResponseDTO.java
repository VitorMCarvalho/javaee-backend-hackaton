package com.stefanini.dto;

import com.stefanini.models.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class UserResponseDTO {
    public String nome;

    public String login;

    public String email;

    public LocalDate dataNascimento;

    private LocalDateTime dataAtualizacao;

    public UserResponseDTO(Usuario user){
        this.nome = user.getNome();
        this.login = user.getLogin();
        this.email = user.getEmail();
        this.dataNascimento = user.getDataNascimento();
        this.dataAtualizacao = user.getDataAtualizacao();
    }
}
