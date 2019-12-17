import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Miejscowosci")
@Getter
@Setter
public class Miejscowosci {
    @Id
    @Column(name = "Id_miejscowosci")
    private int idMiejscowosci;
    @Column(name = "Nazwa_miejsc")
    private String nazwa;

    public Miejscowosci(){

    }
    public Miejscowosci(int idMiejscowosci,String nazwa){
        this.idMiejscowosci = idMiejscowosci;
        this.nazwa = nazwa;
    }
}
