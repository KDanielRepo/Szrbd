import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Trasy")
@Getter
@Setter
public class Trasy {
    @Id
    @Column(name = "Id_trasy")
    private int idTrasy;
    @Column(name = "Id_miejscowosci")
    private int idMiejscowosci;
    @Column(name = "Mie_Id_miejscowosci")
    private int mieIdMiejscowosci;
}
