import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "Kierowcy")
public class Kierowcy{
    @Id
    @Column(name = "Id_kierowcy")
    private Integer idKierowcy;
    @Column(name = "Id_autokaru")
    private Integer idAutokaru;
    @Column(name = "imie")
    private String imie;
    @Column(name = "nazwisko")
    private String nazwisko;
    @Column(name = "pensja")
    private Float pensja;

    public Kierowcy(){

    }
    public Kierowcy(Integer idKierowcy, Integer idAutokaru,String imie,String nazwisko, Float pensja){
        this.idKierowcy = idKierowcy;
        this.idAutokaru = idAutokaru;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pensja = pensja;
    }

}
