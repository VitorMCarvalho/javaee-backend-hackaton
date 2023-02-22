package com.stefanini.repositories;

import com.stefanini.dao.GenericDAO;
import com.stefanini.dto.UserCreationDTO;
import com.stefanini.dto.UserResponseDTO;
import com.stefanini.models.Usuario;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class UserRepo extends GenericDAO<Usuario, Long> {
    @Transactional
    public UserResponseDTO userCreate(Usuario user){
        this.save(user);
        return new UserResponseDTO(user);
    }

}
