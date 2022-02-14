package ru.kpfu.itis.repositories;

import ru.kpfu.itis.form.ProfileSongsForm;
import ru.kpfu.itis.models.Songs;

import java.util.List;
import java.util.Optional;

public interface SongsRepositories {

    List<Songs> findAllInCatalogNew();

    public Optional<Songs> findById(Integer id);
    public List<Songs> findAllInWishList(String email);

    void dropSongWishList(ProfileSongsForm form);

    void addToList(ProfileSongsForm form);

    Optional<ProfileSongsForm> songIsNewInList(ProfileSongsForm form);

}
