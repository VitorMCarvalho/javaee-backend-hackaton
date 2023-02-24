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
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {

    public Long id;

    public String nome;

    public String login;

    public String email;

    public LocalDate dataNascimento;

    public UserResponseDTO(Usuario user){
        this.nome = user.getNome();
        this.id = user.getId();
        this.login = user.getLogin();
        this.email = user.getEmail();
        this.dataNascimento = user.getDataNascimento();
    }
}
