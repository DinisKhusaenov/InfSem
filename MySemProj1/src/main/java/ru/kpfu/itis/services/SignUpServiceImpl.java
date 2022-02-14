package ru.kpfu.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.form.UserForm;
import ru.kpfu.itis.models.User;
import ru.kpfu.itis.repositories.UserRepositoryJdbcImpl;

@Service
public class SignUpServiceImpl {

    UserRepositoryJdbcImpl userRepositoryJdbs;

    @Autowired
    public SignUpServiceImpl(UserRepositoryJdbcImpl userRepositoryJdbs) {
        this.userRepositoryJdbs = userRepositoryJdbs;
    }

    public void signUp(UserForm form) {
        User user = User.builder()
                .email(form.getEmail())
                .name(form.getName())
                .password(form.getPassword())
                .lastName(form.getLastName())
                .build();
        userRepositoryJdbs.saveUser(user);
    }

    public boolean isNewUser(String email) {
        return userRepositoryJdbs.findByEmail(email).isPresent();
    }
}

