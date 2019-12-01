import lombok.Getter;
@Getter
public class Bilet {
    public Bilet(){

    }
    public Bilet(int idBilet, int idSiedzenie, float cena){
        this.idBilet = idBilet;
        this.idSiedzenie = idSiedzenie;
        this.cena = cena;
    }
    private int idBilet;
    private int idSiedzenie;
    private float cena;
}
