
import co.edu.uniquindio.poo.Billetera;
import co.edu.uniquindio.poo.Usuario;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class BilleteraTest {

    @Test
    public void generarCodigoBilleteraTest() {
        Usuario usuario = new Usuario("Pedro", "Gonzales", "pedro@gmail.com", "Cra 24#35-23", 3173407617L, "123");
        Billetera billetera = new Billetera(usuario, 120);
        System.out.println(billetera);
    }
}
