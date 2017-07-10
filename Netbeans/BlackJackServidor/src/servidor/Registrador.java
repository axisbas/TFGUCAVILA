/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servidor;

import baseDatos.BaseD;
import controlador.Controlador;
import java.sql.SQLException;


/**
 *
 * @author alex
 */
public class Registrador {


    private String nombre;
    private String password;
    private String email;
    private static Registrador registrador = null;


    public static Registrador getInstancia(){
        if (registrador == null){
            registrador = new Registrador();
        }
        return registrador;
    }
    
    public boolean darAlta (String nombre, String password, String email, int puntos) throws SQLException{
        if (esNombreValido(nombre) && esPassValido(password) && esMailValido(email)){
        return BaseD.getInstancia().darAlta(nombre, password, email, puntos);
        }
        else return false;
    }

    public int validarUsuario (String nombre, String password) throws SQLException{
        return BaseD.getInstancia().validarUsuario(nombre, password);
    }

    public boolean esPassValido (String password){
        if (password.length() >= 4){
            return true;
        }
        return false;
    }

    public boolean esNombreValido (String nombre){
        for (UsuarioRegistrado users : Controlador.getInstancia().getUsuarios()) {
            if(users.getNombre().equals(nombre)) return false;
        }
        return true;
    }

    public boolean esMailValido (String email){
        if (email.length()>=1){
            if (email.contains("@")){
            return true;
            }
        }
        return false;
    }
}

