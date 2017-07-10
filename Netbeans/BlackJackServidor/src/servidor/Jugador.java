/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

/**
 *
 * @author alex
 */
public class Jugador {

    private boolean miron = true;
    private UsuarioRegistrado usuario;
    private Mano mano = new Mano();
    private int apuesta;
    private Mesa mesa;
    private boolean plantado = false;



    public Jugador(Mesa m, UsuarioRegistrado u) {
        this.mesa = m;
        this.usuario = u;
    }

    public boolean isPlantado() {
        return plantado;
    }

    public String getNombre(){
        if (this.getUsuarioRegistrado()==null){
            return "crupier";
        }
        else return this.getUsuarioRegistrado().getNombre();
    }

    public void setPlantado(boolean plantado) {
        this.plantado = plantado;
    }

    protected Jugador (){

    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }



    public Mano getMano() {
        return mano;
    }

    public void resetearMano() {
        mano.resetear();
        this.setPlantado(false);
    }

    public Jugador(UsuarioRegistrado usuario) {
        this.usuario = usuario;
    }

    public UsuarioRegistrado getUsuarioRegistrado() {
        return usuario;
    }

    /**
     * @return the miron
     */
    public boolean isMiron() {
        return miron;
    }

    public int getApuesta() {
        if (miron) {
            return 0;
        }
        return apuesta;
    }

    public void setApuesta(int apuesta) {
        this.apuesta = apuesta;
    }


    /**
     * @param miron the miron to set
     */
    public void setMiron(boolean miron) {
        this.miron = miron;
    }
}
