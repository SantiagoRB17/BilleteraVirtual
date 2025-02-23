
import co.edu.uniquindio.poo.BilleteraVirtual;
import co.edu.uniquindio.poo.Usuario;
import org.junit.jupiter.api.Test;

public class BilleteraTest {

    @Test
    public void generarCodigoBilleteraTest() {
        Usuario usuario = new Usuario("Pedro", "Gonzales", "pedro@gmail.com", "Cra 24#35-23", 3173407617L, "123");
        BilleteraVirtual billetera = new BilleteraVirtual(usuario, 120);
        System.out.println(billetera);
    }
}
