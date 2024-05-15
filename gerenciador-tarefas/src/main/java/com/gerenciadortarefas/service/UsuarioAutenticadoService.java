package com.gerenciadortarefas.service;

import com.gerenciadortarefas.entity.Usuario;
import com.gerenciadortarefas.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class UsuarioAutenticadoService implements UserDetailsService {

    @Autowired
    private IUsuarioRepository iUsuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = iUsuarioRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("usuário não cadastrado " + username));

        List<SimpleGrantedAuthority> roles = usuario.getRoles().stream().map(
                role -> new SimpleGrantedAuthority(role.getNome().toString())).collect(Collectors.toList());

        return new User(usuario.getUsername(), usuario.getPassword(), roles);
    }
}
