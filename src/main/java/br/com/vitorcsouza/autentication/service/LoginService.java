package br.com.vitorcsouza.autentication.service;

import br.com.vitorcsouza.autentication.dto.LoginDto;
import br.com.vitorcsouza.autentication.model.Login;
import br.com.vitorcsouza.autentication.repository.LoginRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;

@Service
public class LoginService implements UserDetailsService {

    @Autowired
    LoginRespository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsuario(username);
    }

    public LoginDto cadastro(LoginDto dto) {

        Login login = new Login(dto);
        repository.save(login);

        dto.setId(login.getId());

        return dto;
    }

}
