import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Siedzenie")
@Getter
@Setter
public class Siedzenie {
    @Id
    @Column(name = "Id_siedzenia")
    private Integer idSiedzenia;
    @Column(name = "Id_biletu")
    private Integer idBiletu;
    @Column(name = "Id_autokaru")
    private Integer idAutokaru;
    public Siedzenie(){

    }
    public Siedzenie(Integer idSiedzenia, Integer idBiletu, Integer idAutokaru){
        this.idSiedzenia = idSiedzenia;
        this.idAutokaru = idAutokaru;
        this.idBiletu = idBiletu;
    }
}
