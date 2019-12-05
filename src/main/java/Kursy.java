import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Kursy")
@Getter
@Setter
public class Kursy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_kursu")
    private int idKursu;
    @Column(name = "Id_trasy")
    private int idTrasy;
    @Column(name = "Data_odjazdu")
    private Date dataOdjazdu;
    @Column(name = "Data_przyjazdu")
    private Date dataPrzyjazdu;
}
