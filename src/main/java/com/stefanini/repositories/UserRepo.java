package com.stefanini.repositories;

import com.stefanini.dao.GenericDAO;
import com.stefanini.dto.UserResponseDTO;
import com.stefanini.models.Usuario;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

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
}
