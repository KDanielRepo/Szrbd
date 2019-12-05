import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "Bilety")
public class Bilet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_biletu")
    private int idBilet;
    @Column(name = "Id_siedzenia")
    private int idSiedzenie;
    @Column(name = "cena")
    private float cena;
    public Bilet(){

    }
    public Bilet(int idBilet, int idSiedzenie, float cena){
        this.idBilet = idBilet;
        this.idSiedzenie = idSiedzenie;
        this.cena = cena;
    }
    /*@Override
    public String toString(){
        return "Bilet [id="+idBilet+
    }*/
}
