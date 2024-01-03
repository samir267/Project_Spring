package SpringProject.web.requests;

import java.time.LocalDateTime;
import java.util.Date;

import SpringProject.dao.models.Car;
import io.micrometer.common.lang.Nullable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class ContractForm {
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_car", referencedColumnName = "id")
    private Car car;
    private LocalDateTime date_deb;
    private LocalDateTime date_fin;
        private int prix_Contract;
    
}