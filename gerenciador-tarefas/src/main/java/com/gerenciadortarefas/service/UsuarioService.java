package com.gerenciadortarefas.service;

import com.gerenciadortarefas.entity.Usuario;
import com.gerenciadortarefas.repository.IRoleRepository;
import com.gerenciadortarefas.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private IUsuarioRepository iUsuarioRepository;

    @Autowired
    private IRoleRepository iRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario salvarUsuario(Usuario usuario){

        usuario.setRoles(usuario.getRoles()
                .stream()
                .map(role -> iRoleRepository.findByNome(role.getNome()))
                .toList());

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return this.iUsuarioRepository.save(usuario);
    }

    public Usuario atualizarUsuario(Usuario usuario){

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return this.iUsuarioRepository.save(usuario);
    }

    public void excluirUsuario(Usuario usuario){
        this.iUsuarioRepository.deleteById(usuario.getId());
    }

    public List<Usuario> obtemUsuarios(){
        return this.iUsuarioRepository.findAll();
    }

}
