package com.gerenciadortarefas.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gerenciadortarefas.entity.UsuarioAutenticado;
import com.gerenciadortarefas.service.AutenticacaoService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;
import java.util.Collections;
import java.util.stream.Collectors;

public class LoginFiltro extends AbstractAuthenticationProcessingFilter {

    public LoginFiltro(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String collect = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        UsuarioAutenticado usuarioAutenticado = new ObjectMapper().readValue(collect, UsuarioAutenticado.class);

        return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(

                usuarioAutenticado.getUsername(),
                usuarioAutenticado.getPassword(),
                Collections.emptyList()

        ));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, Authentication authentication){

        AutenticacaoService.addJWTToken(response, authentication);

    }

}
