package ru.kpfu.itis.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.form.ProfileSongsForm;
import ru.kpfu.itis.models.Songs;

import javax.sql.DataSource;
import java.util.*;

@Repository
public class SongsRepositoriesJdbcImpl implements SongsRepositories{

    //language=SQL
    private static final String SQL_SELECT_ALL_SONGS_CATALOG_NEW = "select * from songs order by id desc";
    //language=SQL
    private static final String SQL_SELECT_BY_ID = "select * from songs where songs.id = :id";
    //language=SQL
    private static final String SQL_SELECT_ALL_SONGS_WISH_LIST="select * from songs where\n" +
            " id in (Select song_id from wish_list where user_email=:email);";
    //language=SQL
    private static final String SQL_DELETE_SONG_WISH_LIST = "delete from wish_list where song_id= :id and user_email=:email;";
    //language=SQL
    private static final String SQL_INSERT_TO_WISH_LIST = "INSERT INTO wish_list(user_email, song_id) VALUES(:email, :id)";
    //language=SQL
    private static final String SQL_IS_NEW_PRODUCT_IN_LIST =
            "SELECT song_id, user_email from wish_list where user_email = :email and song_id = :id;";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public SongsRepositoriesJdbcImpl(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    private final RowMapper<Songs> songsRowMapper = (row, rowNumber) -> Songs.builder()
            .id(row.getInt("id"))
            .name(row.getString("song_name"))
            .picture(row.getString("picture"))
            .build();

    private final RowMapper<ProfileSongsForm> songsFormRowMapper = (row, rowNumber) -> ProfileSongsForm.builder()
            .id(row.getInt("song_id"))
            .email(row.getString("user_email"))
            .build();


    private final SqlParameterSource createMap(ProfileSongsForm form){
        Map<String, Object> values = new HashMap<>();
        values.put("id", form.getId());
        values.put("email", form.getEmail());
        SqlParameterSource parameterSource = new MapSqlParameterSource(values);
        return parameterSource;
    }

    @Override
    public List<Songs> findAllInCatalogNew() {
        return namedParameterJdbcTemplate.query(SQL_SELECT_ALL_SONGS_CATALOG_NEW, songsRowMapper);
    }

    @Override
    public Optional<Songs> findById(Integer id) {
        try {
            return Optional.of(namedParameterJdbcTemplate.queryForObject(SQL_SELECT_BY_ID,
                    Collections.singletonMap("id", id) , songsRowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Songs> findAllInWishList(String email) {
        return namedParameterJdbcTemplate.query(SQL_SELECT_ALL_SONGS_WISH_LIST,
                Collections.singletonMap("email", email) , songsRowMapper);
    }

    @Override
    public void dropSongWishList(ProfileSongsForm form) {
        namedParameterJdbcTemplate.update(SQL_DELETE_SONG_WISH_LIST, createMap(form));
    }

    @Override
    public void addToList(ProfileSongsForm form){
        namedParameterJdbcTemplate.update(SQL_INSERT_TO_WISH_LIST, createMap(form));
    }

    @Override
    public Optional<ProfileSongsForm> songIsNewInList(ProfileSongsForm form) {
        try {
            return Optional.of(namedParameterJdbcTemplate.queryForObject(SQL_IS_NEW_PRODUCT_IN_LIST,
                    createMap(form), songsFormRowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

}
