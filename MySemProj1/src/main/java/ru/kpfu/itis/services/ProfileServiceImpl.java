package ru.kpfu.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.models.User;
import ru.kpfu.itis.repositories.UserRepositoryJdbcImpl;
import ru.kpfu.itis.repositories.UsersRepository;

@Service
public class ProfileServiceImpl  {
    UserRepositoryJdbcImpl userRepositoryJdbsImpl;

    @Autowired
    public ProfileServiceImpl(UserRepositoryJdbcImpl userRepositoryJdbsImpl) {
        this.userRepositoryJdbsImpl=userRepositoryJdbsImpl;
    }

    public User getUser(String email) {
        try {
            return (User) userRepositoryJdbsImpl.findByEmail(email).orElseThrow(IllegalArgumentException::new);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }
}
