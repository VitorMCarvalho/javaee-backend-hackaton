package com.stefanini.repositories;

import com.stefanini.dao.GenericDAO;
import com.stefanini.dto.UserResponseDTO;
import com.stefanini.models.Usuario;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserRepo extends GenericDAO<Usuario, Long> {
    @Transactional
    public UserResponseDTO updateUser(Usuario user){
        this.update(user);
        return new UserResponseDTO(user);
    }
    @Transactional
   public Usuario findByLogin(String login){
        try{
            return (Usuario) this.createQuery("SELECT usuario FROM Usuario usuario WHERE usuario.login = :login")
                    .setParameter("login", login)
                    .getSingleResult();
        }catch (Exception e){
            return null;
        }
   }
    @Transactional
    public List<Usuario> listByBirthdays(int mes){
        try{
            return this.createQuery("SELECT usuario from Usuario usuario where month(usuario.dataNascimento) = :mes", Usuario.class)
                    .setParameter("mes", mes)
                    .getResultList();
        }catch (Exception e){
            return null;
        }
    }

    @Transactional
    public List<String> listByEmails(){
        List<String> resultList = this.createQuery("SELECT DISTINCT SUBSTRING(email, LOCATE('@', email) " +
                        "+ 1) from Usuario", String.class)
                .getResultList();

        return resultList;
    }
}
