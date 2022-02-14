package ru.kpfu.itis.models;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class User {
    private Integer id;
    private String name;
    private String email;
    private String password;
    private String lastName;
}
