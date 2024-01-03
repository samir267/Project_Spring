package SpringProject.dao.models;

import java.time.LocalDateTime;
import java.util.Date;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Contracts")
public class Contracts {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_car", referencedColumnName = "id")
    private Car car;
    private LocalDateTime date_deb;
    private LocalDateTime date_fin;
    private int prix_Contract;

    
    public Long getId_car() {
        return car != null ? car.getId() : null;
    }
    
    
    public void setId_car(Long id) {
        if (car == null) {
            car = new Car();
        }
        car.setId(id);
    }
    
    

    
}