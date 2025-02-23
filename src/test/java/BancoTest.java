
import co.edu.uniquindio.poo.Banco;
import co.edu.uniquindio.poo.BilleteraVirtual;
import co.edu.uniquindio.poo.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la clase banco
 */
public class BancoTest {
    private Banco banco;

    /**
     *Se crea una lista de usuarios y billeteras junto con una instancia de banco para las pruebas
     */
    @BeforeEach
    public void crearDatosPrueba() {

        ArrayList<Usuario> usuarios=new ArrayList<>();
        ArrayList<BilleteraVirtual> billeteras=new ArrayList<>();

        Usuario usuario1=new Usuario("Pepe","Hernandez","hernan@gmail.com",
                "cra 24#35-23",321781912L,"1234");
        Usuario usuario2=new Usuario("Maria","Taborda","mara@gmail.com",
                "cra 32#12-43",321781122L,"124");
        Usuario usuario3=new Usuario("Pablo","Gomez","pabgom@gmail.com",
                "cra 12#34-22",3217332L,"152");

        usuarios.add(usuario1);
        usuarios.add(usuario2);
        usuarios.add(usuario3);

        BilleteraVirtual billetera1=new BilleteraVirtual(usuario1,2000);
        BilleteraVirtual billetera2=new BilleteraVirtual(usuario3,3000);
        BilleteraVirtual billetera3= new BilleteraVirtual(usuario2,4000);
        banco=new Banco();
        billetera1.setCodigoUnico(banco.generarCodigoUnico());
        billetera2.setCodigoUnico(banco.generarCodigoUnico());
        billetera3.setCodigoUnico(banco.generarCodigoUnico());

        billeteras.add(billetera1);
        billeteras.add(billetera2);
        billeteras.add(billetera3);

        banco.setUsuarios(usuarios);
        banco.setBilleteras(billeteras);

    }

    /**
     * Prueba para el metodo de agregar usuario
     * -Se espera que no arroje una excepcion al usar el metodo de agregar usuario
     * -Se espera que al usar el metodo de agregar usuario la lista de usuarios no este vacia
     */
    @Test
    public void agregarUsuarioTest() {

        Usuario usuario=new Usuario("Sara","londoño","londo@gmail.com",
                "cra 12#45-13",32168518L,"184");
        assertDoesNotThrow( ()-> banco.agregarUsuario(usuario));
        ArrayList<Usuario> usuarios =banco.getUsuarios();
        assertNotNull(usuarios);
    }

    /**
     * Prueba para el metodo eliminar usuario
     * -Se espera que no arroje ninguna excepcion el metodo de eliminar
     * -Se espera que el que usuario con el id 1234 ya no exista en la lista de usuarios del banco
     */
    @Test
    public void eliminarUsuarioTest() {
        assertDoesNotThrow( ()-> banco.eliminarUsuario("1234"));

        Usuario usuario=banco.obtenerUsuario("1234");
        assertNull(usuario);
    }

    /**
     * Metodo de prueba para el metodo actualizar usuario
     * -Se espera que no arroje ninguna excepcion el metodo de actualizar
     * -Se espera que no este nulo el usuario actualizado
     * -Se espera que el usuario tenga los datos actualizados
     */
    @Test
    public void actualizarUsuarioTest() {
        Usuario nuevoUsuario=new Usuario("Santiago","Hernandez","hernan@gmail.com",
                "cra 24#35-23",321781912L,"1234");

        assertDoesNotThrow(()-> banco.actualizarUsuario(nuevoUsuario));
        Usuario usuarioActualizado=banco.obtenerUsuario("1234");
        assertNotNull(usuarioActualizado);
        assertEquals("Santiago",usuarioActualizado.getNombre());
        assertEquals("Hernandez",usuarioActualizado.getApellido());
        assertEquals(321781912L,usuarioActualizado.getTelefono());
        assertEquals("cra 24#35-23",usuarioActualizado.getDireccion());
        assertEquals("hernan@gmail.com",usuarioActualizado.getCorreo());
    }

    /**
     * Prueba para el metodo de obtener usuario
     * -Se espera que la variable que almacena al usuario buscado no este vacia
     * -Se espera que el nombre, apellido y correo coincidad con el del usuario buscado
     */
    @Test
    public void obtenerUsuario() {
        Usuario usuario= banco.obtenerUsuario("124");

        assertNotNull(usuario);
        assertEquals("Maria",usuario.getNombre());
        assertEquals("Taborda",usuario.getApellido());
        assertEquals("mara@gmail.com",usuario.getCorreo());
    }

    /**
     * Prueba para el metodo de generar codigo billetera
     * -Se espera que el codigo generado sea de 10 digitos
     */
    @Test
    public void generarCodigoBilleteraTest() {
        String codigo=banco.generarCodigoUnico();
        assertEquals(10, codigo.length());
    }

    /**
     * Prueba para el metodo que asigna un codigo unico a la billetera
     * -Se espera que la variable "encontrado" siempre sea false, lo que indica que el
     * codigo unico nunca se repite en las billeteras
     */
    @Test
    public void asignarCodigoUnicoTest() {
        Usuario usuario5=new Usuario("Jaime","Ochoa","jaoch@gmail.com",
                "cra 45#9-10",32149086L,"290");
        BilleteraVirtual billetera4=new BilleteraVirtual(usuario5,8000);
        banco.asignarCodigoBilletera(billetera4);

        boolean encontrado=false;

        for(BilleteraVirtual b : banco.getBilleteras()){
            if(b.getCodigoUnico().equals(billetera4.getCodigoUnico())){
                encontrado=true;
            }
        }
        assertFalse(encontrado);

    }

    /**
     * Prueba para el metodo de obtener billetera
     * -Para obtener una billetera se busca por el id del usuario asociado a esa billetera
     * -Se espera que la variable que contiene a la billetera buscada nunca sea nula
     * -Se espera que los datos del usuario asociado a la billetera buscada coincidan con los datos del usuario obtenidos
     */
    @Test
    public void obtenerBilleteraTest() {

        BilleteraVirtual billeteraBuscada=banco.obtenerBilletera("152");

        assertNotNull(billeteraBuscada);
        assertEquals("Pablo",billeteraBuscada.getUsuario().getNombre());
        assertEquals("Gomez",billeteraBuscada.getUsuario().getApellido());
        assertEquals("cra 12#34-22",billeteraBuscada.getUsuario().getDireccion());
        assertEquals(3217332L,billeteraBuscada.getUsuario().getTelefono());


    }

    /**
     *Prueba para metodo de agregar billetera
     * -Se crea un escenario donde se trata de añadir una billetera con un usuario que ya tiene una billetera asociada
     * -Se espera que arroje una expecion donde indique el usuario ya tiene una billetera asociada
     */
    @Test
    public void agregarBilleteraTest(){
        Usuario usuario=banco.getUsuarios().get(1);
        BilleteraVirtual billetera=new BilleteraVirtual(usuario,2000);

        assertThrows(Exception.class, ()->banco.agregarBilletera(billetera));
    }
}
