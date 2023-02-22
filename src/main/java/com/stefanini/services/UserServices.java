package com.stefanini.services;

import com.stefanini.dto.UserCreationDTO;
import com.stefanini.dto.UserResponseDTO;
import com.stefanini.exceptions.BadRequestException;
import com.stefanini.exceptions.UserException;
import com.stefanini.models.Usuario;
import com.stefanini.repositories.UserRepo;
import com.stefanini.utils.Criptogafador;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import java.time.LocalDate;

import static com.stefanini.intefaces.RegexValidator.*;

@ApplicationScoped
public class UserServices {

    @Inject
    private UserRepo usuarioRepository;

    public UserResponseDTO userCreate(UserCreationDTO userDTO) throws UserException {
        Usuario user = new Usuario(userDTO);
        user.setSenha(Criptogafador.decriptografar(user.getSenha()));
        this.verificarIntegridade(user);
        return usuarioRepository.userCreate(user);
    }

    public void verificarIntegridade(Usuario user) throws UserException {
        this.nomeValido(user.getNome());
        this.loginValido(user.getLogin());
        this.emailValido(user.getEmail());
        this.senhaValido(user.getSenha());
        this.nascimentoValido(user.getDataNascimento());

    }

    public void nomeValido(String nome) throws UserException {
        if(nome.isBlank()){
            throw new UserException("Nome nao pode ser Branco");
        }else if(nome==null) {
            throw new UserException("Nome nao pode ser Nulo");
        }else if(nome.length() > 50){
            throw new UserException("Nome deve ter menor ou igual a 50 caracteres");
        }
    }
    public void loginValido(String login) throws UserException {
        if(login.isBlank()){
            throw new UserException("login nao pode ser Branco");
        }else if(login==null) {
            throw new UserException("login nao pode ser Nulo");
        }else if(login.length() > 20 && login.length() < 5) {
            throw new UserException("login deve possuit entre 5 e 20 caracteres");
        }
    }

    public void emailValido(String email) throws UserException {
        if(email.isBlank()){
            throw new UserException("email nao pode ser Branco");
        }else if(email==null) {
            throw new UserException("email nao pode ser Nulo");
        }else if(email.length() < 10 ){
            throw new UserException("email deve ter mais que 10 caractres");
        }else if(!email.matches(EMAIL_REGEX)){
            throw new UserException("dados de email invalidos");
        }
    }

    public void senhaValido(String senha) throws UserException {
        if(senha.isBlank()){
            throw new UserException("senha nao pode ser Branco");
        }else if(senha==null) {
            throw new UserException("senha nao pode ser Nulo");
        }else if(senha.length() > 10){
            throw new UserException("email deve ter menos que 10 caractres");
        }else if(senha.length() < 4){
            throw new UserException("email deve ter mais que 4 caractres");
        }else if(senha.matches(PASSWORD_REGEX)){
            throw new UserException("senha deve obedecer o padrao");
        }
    }

    public void nascimentoValido(LocalDate nascimento) throws UserException {
        if(nascimento==null) {
            throw new UserException("data de nascimento nao pode ser Nulo");
        }else if(nascimento.toString().matches(DATA_ANIVERSARIO_REGEX)){
            throw new UserException("data de nascimento nao pode ser invalida");
        }
    }
}
