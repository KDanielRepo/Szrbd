import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Autokary")
@Getter
@Setter
public class Autokary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_autokaru")
    private int idAutokaru;
    @Column(nullable = true,name = "Id_kierowcy")
    private Integer idKierowcy;
    @Column(name = "Nr_rej")
    private String nrRej;
    @Column(name = "Marka")
    private String marka;
    @Column(name = "Model")
    private String model;

    public Autokary(){

    }
    public Autokary(int idAutokaru,int idKierowcy,String nrRej,String marka,String model){
        this.idAutokaru = idAutokaru;
        this.idKierowcy = idKierowcy;
        this.nrRej = nrRej;
        this.model = model;
        this.marka = marka;

    }
}
