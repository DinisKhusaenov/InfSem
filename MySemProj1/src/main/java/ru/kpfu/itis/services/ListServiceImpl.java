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
public class ListServiceImpl {

    private final SongsRepositories songRepository;

    @Autowired
    public ListServiceImpl(SongsRepositories songsRepository) {
        this.songRepository = songsRepository;
    }

    public SongsForm getSong(Integer id) {
        Songs songs = songRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return from(songs);
    }

    public void addSongToList(ProfileSongsForm form){
        songRepository.addToList(form);
    }

    public boolean isNewSongInList(ProfileSongsForm form) {
        return songRepository.songIsNewInList(form).isPresent();
    }

    public List<SongsForm> getAllSongsNew() {
        return from(songRepository.findAllInCatalogNew());
    }
}
