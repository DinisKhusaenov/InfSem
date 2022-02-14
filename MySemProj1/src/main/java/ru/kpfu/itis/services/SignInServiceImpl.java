package ru.kpfu.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.form.LoginForm;
import ru.kpfu.itis.models.User;
import ru.kpfu.itis.repositories.UserRepositoryJdbcImpl;
import ru.kpfu.itis.repositories.UsersRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
public class SignInServiceImpl {
    private final static String PAGE_PERSON_COOKIE_NAME = "pagePerson";

    private final static int PERSON_COOKIE_MAX_AGE = 86400;

    UserRepositoryJdbcImpl crudRepository;

    @Autowired
    public SignInServiceImpl(UserRepositoryJdbcImpl crudRepository) {
        this.crudRepository = crudRepository;
    }

    public boolean correctSignIn(LoginForm form){
        Optional<User> users = crudRepository.findByEmail(form.getEmail());
        return users.isPresent() && users.get().getPassword().equals(form.getPassword());
    }

    public void createCookie(HttpServletResponse response, String login){
        Cookie cookie = new Cookie(PAGE_PERSON_COOKIE_NAME, login);
        cookie.setMaxAge(PERSON_COOKIE_MAX_AGE);
        response.addCookie(cookie);
    }
}
