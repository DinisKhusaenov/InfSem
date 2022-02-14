package ru.kpfu.itis.repositories;

import ru.kpfu.itis.models.User;
import java.util.Optional;


public interface UsersRepository <T, K> {
    Optional<User> findByEmail(String email);

    Optional<User> findPassByEmail(String email);

    void saveUser(User user);

    void updatePass(String email, String password);
}
