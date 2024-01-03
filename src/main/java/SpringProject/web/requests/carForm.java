package SpringProject.web.requests;

import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class carForm {
     @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Nullable
    private String model;
    @Nullable
    private String name;
     @Nullable
    private String category;
    @Nullable
    private String price;
    @Nullable
    private String nb_places;
    @Nullable
    private int etat;
    @Nullable
    private MultipartFile image;
}