import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "Kierowcy")
public class Kierowcy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_kierowcy")
    private int idKierowcy;
    @Column(name = "Id_autokaru")
    private int idAutokaru;
    @Column(name = "imie")
    private String imie;
    @Column(name = "nazwisko")
    private String nazwisko;
    @Column(name = "pensja")
    private int pensja;

    public Kierowcy(){

    }
    public Kierowcy(int idKierowcy, int idAutokaru,String imie,String nazwisko, int pensja){
        this.idKierowcy = idKierowcy;
        this.idAutokaru = idAutokaru;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pensja = pensja;
    }

}
