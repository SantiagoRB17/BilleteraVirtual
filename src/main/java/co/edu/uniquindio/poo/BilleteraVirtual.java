package co.edu.uniquindio.poo;

public class BilleteraVirtual {
    private Usuario usuario;
    private float saldo;
    private  String codigoUnico;
    private final float Costo =200;

    public BilleteraVirtual(Usuario usuario, float saldo) {
        this.usuario = usuario;
        this.saldo = saldo;
        this.codigoUnico = "";
    }

    public Usuario getUsuario() {

        return usuario;
    }

    public void setUsuario(Usuario usuario) {

        this.usuario = usuario;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public float getCosto() {
        return Costo;
    }

    public String getCodigoUnico() {
        return codigoUnico;
    }

    public void setCodigoUnico(String codigoUnico) {
        this.codigoUnico = codigoUnico;
    }

    @Override
    public String toString() {
        return "Billetera{" +
                "usuario=" + usuario +
                ", saldo=" + saldo +
                ", Costo=" + Costo +
                '}';
    }
}
