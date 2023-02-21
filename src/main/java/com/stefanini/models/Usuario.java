package com.stefanini.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Table(name = "TB_USER")
public class Usuario {
    public Usuario(String nome, String login, String email, String senha, Date dataNascimento, Date dataCriacao) {
        this.nome = nome;
        this.login = login;
        this.email = email;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
        this.dataCriacao = dataCriacao;
    }

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "USER_NAME")
    @Max(value=50, message = "Name should not be greater than 50")
    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be null")
    private String nome;

    @Column(name = "USER_NAME", unique = true)
    @NotNull(message = "Login cannot be null")
    @NotBlank(message = "Login cannot be null")
    @Size(min = 5, max = 20, message = "login need to be between 5 and 20 characteres")
    private String login;

    @Column(name = "USER_EMAIL", unique = true)
    @NotNull(message = "Email cannot be null")
    @NotBlank(message = "Email cannot be null")
    @Max(value=10, message = "Name should not be less than 10")
    @Email(message = "Email should be valid")
    private String email;

    @Column(name = "USER_PASSWORD")
    @NotNull(message = "Password cannot be null")
    @NotBlank(message = "Password cannot be null")
    @Size(min = 4, max = 10, message = "Password should be between 4 and 10 characters")
    private String senha;

    @Column(name = "USER_BORN_DATE")
    @NotNull(message = "Password cannot be null")
    @NotBlank(message = "Password cannot be null")
    private Date dataNascimento;

    @Column(name = "USER_ACCOUNT_CREATION_DATE")
    @NotNull(message = "Password cannot be null")
    @NotBlank(message = "Password cannot be null")
    private Date dataCriacao;

    @Column(name = "USER_ACCOUNT_LAST_ATT_DATE")
    @NotNull(message = "Password cannot be null")
    @NotBlank(message = "Password cannot be null")
    private Date dataAtualizacao;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
}
