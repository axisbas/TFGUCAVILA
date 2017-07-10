package mensaje;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alex
 */
public class Mensaje implements Serializable {

    public enum TipoMensaje {

        RespuestaLogin, //Respuesta a petición de login inicial: ok|no/puntos, mensaje explicativo
        RespuestaAlta, //Respuesta a petición de Registro: ok|no/puntos, mensaje explicativo
        AvisoDesconexionCliente,
        MostrarCarta,//Muestra la carta al jugador al cual pertenece: numero/palo
        RespuestaUnirAMesa,//Responde a la petición de UnirAMesa: datos para InfoMesa
        RespuestaSentarseEnMesa,//Responde a la petición de Sentarse en Mesa
        InicioPartida,//El servidor avisa del inicio de la partida y los datos: infomesa
        FinPartida,//El servidor avisa del fin de la partida.(El cliente debe mostrar todas las cartas):mensaje explicativo
        AvisoApuesta,//El servidor avisa de que a un jugador le toca apostar:  mensaje explicativo
        AvisoVictoria,//El servidor avisa de que un jugador está entre los ganadores: nombre, mensaje explicativo
        AvisoTurno,//El servidor avisa de que un jugador va a realizar su jugada: mensaje explicativo
        AvisoFinTurno,//El servidor avisa de que un jugador ha terminado su jugada
        MostrarRanking,//El cliente solicita el ranking de los usuarios
        EnviarPuntuaciones,//El servidor envia la puntuacion del jugador: puntos

        PedirCarta,//El cliente solicita una carta para mejorar su mano: nº mesa
        TerminarMano,//El cliente decide plantarse con las cartas que tiene
        UnirAMesa,//Avisa de que un jugador quiere entrar en una mesa para observar: nºmesa
        SentarseEnMesa,//Petición de sentarse en mesa para jugar la partida como jugador: nºmesa
        SalirDeMesa,//Petición de abandono de mesa e inclusión en la Sala: nº mesa/jugador
        SalirDePartida,//Petición de abandono de partida y cambio de estado a observador: nºmesa
        Apuesta,//Cantidad de puntos que se ponen en juego: nº mesa/puntos
        PasoApuesta,//Descartar la mano y esperar a la siguiente partida:nº mesa
        Login, //Petición de Login: usuario/contraseña
        Alta, //Petición de Alta: usuario/contraseña/email
        RespuestaMostrarRanking //El servidor envia el ranking de los 10 primeros usuarios: nombre1/puntos1/nombre2/../
    };
    public static String separador = "/";
    private TipoMensaje tipo;
    private String datos;
    private String mensaje;

    public String[] splitDatos() {
        return datos.split(separador);
    }

    public Mensaje(TipoMensaje tipo) {
        this.tipo = tipo;
    }

    public Mensaje(TipoMensaje tipo, String datos, String mensaje) {
        this.tipo = tipo;
        this.datos = datos;
        this.mensaje = mensaje;
    }

    public Mensaje(TipoMensaje tipo, String datos) {
        this(tipo, datos, null);
    }

    public String getDatos() {
        return datos;
    }

    public void setDatos(String datos) {
        this.datos = datos;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public TipoMensaje getTipo() {
        return tipo;
    }

    public void setTipo(TipoMensaje tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return tipo.name() + "--" + datos + "--" + mensaje;
    }

    public void enviarMensaje(ObjectOutputStream output) {
        try {
            output.writeObject(this);
            output.flush();
        } catch (Exception e) {
//             System.out.println("Enviar mensaje: " + e);
//             controlador.Controlador.getInstancia().introducirTexto("Error a enviar mensaje: " + e);
        }
    }

    public static Mensaje recibirMensaje(ObjectInputStream input) throws RuntimeException {
        Mensaje m = null;
        try {
            m = (Mensaje) (input.readObject());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return m;
    }
}
