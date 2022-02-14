package ru.kpfu.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.form.ProfileSongsForm;
import ru.kpfu.itis.form.SongsForm;
import ru.kpfu.itis.models.Songs;
import ru.kpfu.itis.repositories.SongsRepositories;

import java.util.List;

import static ru.kpfu.itis.form.SongsForm.from;

@Service
public class WishListServiceImpl{
    private final SongsRepositories songsRepository;

    @Autowired
    public WishListServiceImpl(SongsRepositories productsRepository) {
        this.songsRepository = productsRepository;
    }

    public List<SongsForm> getAllSongs(String email) {
        return from(songsRepository.findAllInWishList(email));
    }

    public SongsForm getSong(Integer id) {
        Songs songs = songsRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return from(songs);
    }

    public void dropSong(ProfileSongsForm form) {
        songsRepository.dropSongWishList(form);
    }

}
