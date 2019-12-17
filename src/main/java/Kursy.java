import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "Kursy")
public class Kursy {
    @Id
    @Column(name = "Id_kursy")
    private Integer idKursu;
    @Column(name = "Id_trasy")
    private Integer idTrasy;
    @Column(name = "Data_odjazdu")
    private Date dataOdjazdu;
    @Column(name = "Data_przyjazdu")
    private Date dataPrzyjazdu;
    public Kursy(){

    }
    public Kursy(Integer idKursu, Integer idTrasy, Date dataOdjazdu, Date dataPrzyjazdu){
        this.idKursu = idKursu;
        this.idTrasy = idTrasy;
        this.dataOdjazdu = dataOdjazdu;
        this.dataPrzyjazdu = dataPrzyjazdu;
    }

}
