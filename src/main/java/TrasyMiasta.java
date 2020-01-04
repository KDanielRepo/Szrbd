import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "Kursy")
public class TrasyMiasta {
    @Id
    @Column(name = "Lp")
    private Integer lp;
    @Column(name = "Id_kursy")
    private Integer idKursu;
    @Column(name = "Nazwa_miejsc")
    private String nazwaMiejsc;
    @Column(name = "Data_odjazdu")
    private Date dataOdjazdu;
    @Column(name = "Data_przyjazdu")
    private Date dataPrzyjazdu;
}
