package ru.kpfu.itis.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.models.Songs;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SongsForm {
    private Integer id;
    private String name;
    private String picture;

    public static SongsForm from(Songs songs) {
        return SongsForm.builder()
                .id(songs.getId())
                .name(songs.getName())
                .picture(songs.getPicture())
                .build();
    }

    public static List<SongsForm> from(List<Songs> songs) {
        return songs.stream().map(SongsForm::from).collect(Collectors.toList());
    }
}
