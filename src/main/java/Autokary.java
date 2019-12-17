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
    @Column(name = "Id_autokaru")
    private Integer idAutokaru;
    @Column(name = "Id_kierowcy")
    private Integer idKierowcy;
    @Column(name = "Nr_rej")
    private String nrRej;
    @Column(name = "Marka")
    private String marka;
    @Column(name = "Model")
    private String model;

    public Autokary(){

    }
    public Autokary(Integer idAutokaru,Integer idKierowcy,String nrRej,String marka,String model){
        this.idAutokaru = idAutokaru;
        this.idKierowcy = idKierowcy;
        this.nrRej = nrRej;
        this.model = model;
        this.marka = marka;

    }
}
