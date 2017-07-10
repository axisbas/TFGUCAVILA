/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import baseDatos.BaseD;
import java.net.ServerSocket;
import java.util.ArrayList;
import mensaje.Mensaje;
import servidor.HiloEscucha;
import servidor.Sala;
import servidor.UsuarioRegistrado;

/**
 *
 * @author alex
 */
public class Controlador {

    private ArrayList<UsuarioRegistrado> usuarios = new ArrayList();
    private static Controlador controlador = null;
    private vista.Vista v;
    private Sala s;
    private ServerSocket servidor = null;
    private static int puerto = 7777;
    private BaseD bd;
    public final static int TiempoEspera = 100;
    public final static int TiempoEsperaPartidas = 10000;

    private Controlador() {
        // s = Sala.getInstancia();
    }

    public static Controlador getInstancia() {
        if (controlador == null) {
            controlador = new Controlador();
        }
        return controlador;
    }

    public void iniciar() {
        conectar();
        if (servidor != null) {
            HiloEscucha hiloe = new HiloEscucha(servidor);
            hiloe.start();

        }
    }

    public static void espera(int tiempoEspera) {
        try {
            Thread.sleep(tiempoEspera);
        } catch (InterruptedException ex) {
        }

    }

    public static void espera() {
        espera(Controlador.TiempoEspera);
    }

    public BaseD getBd() {
        return bd;
    }

    public void conectar() {
        try {
            servidor = new ServerSocket(puerto);
            Controlador.getInstancia().introducirTexto("Se ha creado el socket servidor de escucha");
        } catch (Exception e) {
            Controlador.getInstancia().introducirTexto("Error al crear el socket servidor de escucha: " + e.getMessage());
        }

    }

    public ArrayList<UsuarioRegistrado> getUsuarios() {
        return usuarios;
    }

    public void introducirTexto(String men) {
        vista.Vista.getInstance().introducirTexto(men);
    }

    public void traceTexto(String men) {
        vista.Vista.getInstance().introducirTexto("******" + men);
    }

    public UsuarioRegistrado getUsuario(String nombre) {
        for (UsuarioRegistrado u : usuarios) {
            if (u.getNombre().equals(nombre)) {
                return u;
            }
        }
        return null;
    }

    //Método para desconectar el usuario del Hilo Cliente
    public void desconectaUsuario(UsuarioRegistrado usu) {
        Controlador.getInstancia().introducirTexto("Se ha desconectado el usuario " + usu.getNombre());
        if (usu.getJugador() != null) {
            Mensaje m = new Mensaje(Mensaje.TipoMensaje.AvisoDesconexionCliente);
            usu.getJugador().getMesa().pushMensaje(m, usu.getNombre());
            usu.setJugador(null);
            usuarios.remove(usu);
        }
        //throw new RuntimeException("Método desconectaUsuario");
    }

   public void desconectaUsuarioRegistrado(UsuarioRegistrado usu) {
        Controlador.getInstancia().introducirTexto("Se ha desconectado el usuario " + usu.getNombre());
        usuarios.remove(usu);
    }

    public Sala getSala() {
        return s;
    }

    public void setS(Sala s) {
        this.s = s;
    }

    public boolean existeUsuario(String nombre) {
        return getUsuario(nombre) != null;
    }

    /**
     * Introduce un usuario en la lista de Usuarios Registrador sin hacer comprobación.
     */
    public void anadirUsuario(String nombre, String password, int puntos) {
        UsuarioRegistrado user = new UsuarioRegistrado(nombre, password, puntos);
        usuarios.add(user);
    }
}
