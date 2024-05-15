package com.gerenciadortarefas.repository;

import com.gerenciadortarefas.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

    @Autowired
    Optional<Usuario> findByUsername(String username);
}
