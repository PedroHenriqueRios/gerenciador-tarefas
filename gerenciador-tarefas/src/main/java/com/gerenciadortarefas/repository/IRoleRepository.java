package com.gerenciadortarefas.repository;

import com.gerenciadortarefas.entity.Role;
import com.gerenciadortarefas.entity.Usuario;
import com.gerenciadortarefas.permissoes.PermissaoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {

    Role findByNome(PermissaoEnum nome);

}
