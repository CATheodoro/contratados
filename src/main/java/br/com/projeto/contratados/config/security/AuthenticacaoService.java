package br.com.projeto.contratados.config.security;

import br.com.projeto.contratados.domain.entity.user.User;
import br.com.projeto.contratados.domain.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticacaoService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByEmail(email);

        if(user.isPresent()) {
            return user.get();
        }

        throw new UsernameNotFoundException("E-mail ou senha inválida");
    }
}
