create table users(
     id            BIGSERIAL PRIMARY KEY,
     email         VARCHAR(30),
     name    VARCHAR(30),
     last_name     VARCHAR(30),
     password VARCHAR(100)
);

create table songs(
    id  BIGSERIAL PRIMARY KEY,
    song_name VARCHAR(30),
    picture VARCHAR(500)
);

drop table songs;

create table wish_list(
    user_email VARCHAR(30),
    song_id INTEGER,
    foreign key ("song_id") references "songs" ("id")
);

drop table wish_list;

insert into songs(
                  song_name, picture
) values
          ('Imagine Dragons - Believer', 'DragonsBeliever.png'),
          ('Imagine Dragons - Demons', 'Dragons-Demons.png'),
          ('Ed Sheeran - Perfect', 'Ed-Perfect.png'),
          ('Макс Корж - Тает дым', 'Макс-Тает дым.png');

truncate table songs Cascade;
