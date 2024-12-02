package com.uninassau.service;

import com.uninassau.exception.EmailAlreadyExistsException;
import com.uninassau.exception.InvalidCredentialsException;
import com.uninassau.model.User;
import com.uninassau.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User register(User user) {
        // Verifica se o email já está em uso
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyExistsException("Email já está em uso.");
        }

        // Valida explicitamente a senha
        validatePassword(user.getPassword());

        // Criptografa a senha antes de salvar
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Salva o usuário no banco de dados
        return userRepository.save(user);
    }

    public User login(String email, String password) {
        // Verifica se o email existe no banco de dados
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new InvalidCredentialsException("Email ou senha incorreto."));

        // Verifica se a senha fornecida é válida
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidCredentialsException("Email ou senha incorreto.");
        }

        return user;
    }

    // Metodo para validar as regras da senha
    private void validatePassword(String password) {
        if (password.length() < 8) {
            throw new IllegalArgumentException("A senha deve ter pelo menos 8 caracteres.");
        }

        if (!password.matches(".*\\d.*")) {
            throw new IllegalArgumentException("A senha deve conter pelo menos um número.");
        }

        if (!password.matches(".*[a-zA-Z].*")) {
            throw new IllegalArgumentException("A senha deve conter pelo menos uma letra.");
        }
    }
}
