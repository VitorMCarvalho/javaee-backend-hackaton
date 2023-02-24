package com.stefanini.services;

import com.stefanini.dto.UserCreationDTO;
import com.stefanini.dto.UserResponseDTO;
import com.stefanini.exceptions.*;
import com.stefanini.models.Usuario;
import com.stefanini.repositories.UserRepo;
import com.stefanini.utils.Criptogafador;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.stefanini.intefaces.RegexValidator.*;

@ApplicationScoped
public class UserServices {

    @Inject
    private UserRepo usuarioRepository;

    public UserResponseDTO findById(Long id) throws UserDoesntExistsException, InvalidDataException {
        if(id == null){
            throw new InvalidDataException("id nulo");
        }else if(usuarioRepository.findById(id) == null){
            throw new UserDoesntExistsException("usuario nao existe");
        }else{
            return new UserResponseDTO(usuarioRepository.findById(id));
        }
    }
    public List<UserResponseDTO> listAll() throws UserDoesntExistsException, InvalidDataException {
        return usuarioRepository.listAll().stream().map(user -> new UserResponseDTO((Usuario) user)).collect(Collectors.toList());
    }

    public UserResponseDTO userCreate(UserCreationDTO userDTO) throws UserException {
        this.verificarIntegridade(userDTO);
        this.loginNovo(userDTO.login,true);
        Usuario user = new Usuario(userDTO);
        user.setSenha(Criptogafador.decriptografar(user.getSenha()));
        user.setDataCriacao();
        return new UserResponseDTO(usuarioRepository.save(user));
    }

    public void userDelete(Long id) throws UserException {
        if(id == null){
            throw new InvalidDataException("id nulo");
        }else if(usuarioRepository.findById(id)== null){
            throw new UserDoesntExistsException("usuario nao existe");
        }else{
            usuarioRepository.delete(id);
        }
    }

    public UserResponseDTO userUpdate(UserCreationDTO userDTO) throws BadRequestException {
        Usuario userAchado = usuarioRepository.findById(userDTO.getId());
        if(userAchado == null){
            throw new InvalidDataException("user nao existe");
        }
        this.verificarIntegridade(userDTO);
        this.loginNovo(userDTO.login,false);
        Usuario user = new Usuario(userDTO);
        user.setSenha(Criptogafador.decriptografar(user.getSenha()));
        user.setDataCriacao(userAchado.getDataCriacao());
        user.setDataAtualizacao();
        return usuarioRepository.updateUser(user);
    }

    public List<Usuario> listByBirthday(Integer mes) throws InvalidDataException {
        if(mes>12 || mes<1){
            throw new InvalidDataException("mes deve estar entre 1 e 12");
        }else if(mes == null){
            throw new InvalidDataException("mes nao pode ser nulo");
        }else{
            return usuarioRepository.listByBirthdays(mes);
        }
    }
    public List<String> listByEmails(){
            return usuarioRepository.listByEmails();
    }

    public void verificarIntegridade(UserCreationDTO user) throws UserException {
        this.senhaValido(user.getSenha());
        this.nomeValido(user.getNome());
        this.loginValido(user.getLogin());
        this.emailValido(user.getEmail());
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
            throw new InvalidDataException("login nao pode ser Branco");
        }else if(login==null) {
            throw new InvalidDataException("login nao pode ser Nulo");
        }else if(login.length() > 20 && login.length() < 5) {
            throw new InvalidDataException("login deve possuit entre 5 e 20 caracteres");
       }
    }

    public void loginNovo(String login, Boolean novo) throws UserAlreadyExistsException, UserDoesntExistsException {
        if(novo){
            if(usuarioRepository.findByLogin(login) != null){
                throw new UserAlreadyExistsException("usuario ja existe");
            }
        }else{
            if(usuarioRepository.findByLogin(login) == null){
                throw new UserDoesntExistsException("usuario nao existe");
            }
        }
    }


    public void emailValido(String email) throws UserException {
        if(email.isBlank()){
            throw new InvalidDataException("email nao pode ser Branco");
        }else if(email==null) {
            throw new InvalidDataException("email nao pode ser Nulo");
        }else if(email.length() < 10 ){
            throw new InvalidDataException("email deve ter mais que 10 caractres");
        }else if(!email.matches(EMAIL_REGEX)){
            throw new InvalidDataException("dados de email invalidos");
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
