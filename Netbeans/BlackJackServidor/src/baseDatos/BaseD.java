/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package baseDatos;

import java.sql.*;
import java.util.ArrayList;
import mensaje.Mensaje;
import servidor.Jugador;

/**
 *
 * @author alex
 */
public class BaseD {

    private static BaseD bd = null;
    private Connection conexion = null;

    private BaseD() {
        try {

            //Registramos el driver de MySql
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());

            //Nos conectamos a la base de datos
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/BlackJack", "root", "1234");
            conexion.setAutoCommit(false);
        } catch (Exception e) {
            System.out.println("Error en el registro del driver o de la conexion");
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * Mira si existe un usuario en la Base de Datos
     */
    public boolean existeUsuario(String nombre) throws SQLException {
        Statement comando = conexion.createStatement();
        ResultSet rs = comando.executeQuery("SELECT COUNT(*) as c FROM Usuarios where nombre = '" + nombre + "'");
        rs.next();
        return (rs.getInt("c")) > 0;
    }

    public static BaseD getInstancia() {
        if (bd == null) {
            bd = new BaseD();
        }
        return bd;
    }

    public String mostrarRanking() throws SQLException {//nombre1/puntos1/nombre2.../
        String resultado = "";
        Statement comando = conexion.createStatement();
        ResultSet rs = comando.executeQuery("SELECT nombre,puntos FROM Usuarios ORDER BY puntos DESC LIMIT 10");

        while (rs.next()) {
            resultado += rs.getString("nombre") + "\t" + rs.getInt("puntos") + "\n";
        }
        return resultado;
    }

    /**
     * Puntos si existe, -1 si no.
     * @param nombre
     * @param password
     * @return
     * @throws SQLException
     */
    public int validarUsuario(String nombre, String password) throws SQLException {
        Statement comando = conexion.createStatement();
        ResultSet rs = comando.executeQuery("SELECT puntos FROM Usuarios where nombre = '" + nombre + "' AND password= '" + password + "'");
        if (rs.next()) {
            return rs.getInt("puntos");
        } else {
            return -1;
        }
    }

    public boolean darAlta(String nombre, String password, String email, int puntos) throws SQLException {
        PreparedStatement comando = conexion.prepareStatement("INSERT INTO Usuarios VALUES (?,?,?,?)");
        try {
            comando.setString(1, nombre);
            comando.setString(2, password);
            comando.setString(3, email);
            comando.setInt(4, puntos);
            comando.executeUpdate();
        } catch (SQLException ex) {
            return false;
        }
        return true;
    }

    public void modificarPuntos(int puntos, String nom) {
        try {
            Statement comando = conexion.createStatement();
            comando.executeUpdate("UPDATE usuarios SET puntos = " + puntos + " WHERE nombre = \'" + nom + "\'");
            conexion.commit();
        } catch (Exception e) {
            System.out.println("Error en modificacion de puntos");
            e.printStackTrace();
        }
    }

    @Override
    protected void finalize() {
        try {

            //Cerramos la conexion
            conexion.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registrarPartida(int numeroMesa, int contadorPartida, ArrayList<Jugador> jugadoresActivos, ArrayList<Jugador> ganadores) throws SQLException {
            PreparedStatement comando = conexion.prepareStatement("INSERT INTO Partidas (mesa, partida, jugadores, fecha, ganador) VALUES (?,?,?,?,?)");
            comando.setInt(1, numeroMesa);
            comando.setInt(2, contadorPartida);
            comando.setString(3, this.aplana(jugadoresActivos));
            comando.setDate(4, new java.sql.Date(new java.util.Date().getTime()));
            comando.setString(5, ganadores.isEmpty()? "crupier":this.aplana(ganadores));
            comando.executeUpdate();
    }

    private String aplana (ArrayList<Jugador> jugadores){
        String nombres="";
        for (Jugador j : jugadores){
            nombres+=j.getNombre()+",";
        }
        return nombres;
    }

}
