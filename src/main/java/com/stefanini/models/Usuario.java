package com.stefanini.models;

import com.stefanini.dto.UserCreationDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.stefanini.intefaces.RegexValidator.PASSWORD_REGEX;
import static com.stefanini.intefaces.UserValidator.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_USER")
public class Usuario {

    public Usuario(UserCreationDTO user){
        this.nome = user.getNome();
        this.login = user.getLogin();
        this.email = user.getEmail();
        this.senha = user.getSenha();
        this.dataNascimento = user.getDataNascimento();
        this.dataCriacao = LocalDateTime.now();
        this.dataAtualizacao = LocalDateTime.now();
    }

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "USER_NAME")
    @NotNull(message = NOT_NULL)
    @NotBlank(message = NOT_BLANK)
    private String nome;

    @Column(name = "USER_LOGIN", unique = true)
    @NotNull(message = NOT_NULL)
    @NotBlank(message = NOT_BLANK)
    @Size(min = 5, max = 20, message = "login need to be between 5 and 20 characteres")
    private String login;

    @Column(name = "USER_EMAIL", unique = true)
    @NotNull(message = NOT_NULL)
    @NotBlank(message = NOT_BLANK)
    @Email(message = NOT_VALID)
    private String email;

    @Column(name = "USER_PASSWORD")
    @NotNull(message = NOT_NULL)
    @NotBlank(message = NOT_BLANK)
    @Size(min = 4, max = 10, message = "Password should be between 4 and 10 characters")
    private String senha;

    @Column(name = "USER_BORN_DATE")
    private LocalDate dataNascimento;

    @Column(name = "USER_ACCOUNT_CREATION_DATE")
    private LocalDateTime dataCriacao;

    @Column(name = "USER_ACCOUNT_LAST_ATT_DATE")
    private LocalDateTime dataAtualizacao;
}
