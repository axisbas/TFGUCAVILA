/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beanPictureBox;

import blackjackcliente.Principal;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

/**
 * Esta clase representa un panel con una imagen de fondo.<br>
 * La imagen debe ser asignada mediante el método <b>setImagen()</b> o <b>setRutaImagen</b>.
 * Para dimensionar la imagen debe establecer el tamaño mediante el método
 * <b>setModo()</b>, al cual puede pasarse como argumento uno de los valores de la enumeración
 * <b>PictureBox.Ajuste</b>.<br>
 * La posición de la imagen puede establecerse mediante el método <b>setPosicion()</b>,
 * al cual puede pasarse como parámetro un valor de la enumeración <b>PictureBox.Posicion</b>.
 */
public class PictureBox extends javax.swing.JPanel {

    /**
     * Establece los modos de dimensionamiento de la imagen respecto al fondo:<br>
     * &nbsp;&nbsp;&nbsp;ORIGINAL, para mantener el tamaño original de la imagen.<br>
     * &nbsp;&nbsp;&nbsp;AJUSTAR, para ajustar la imagen a las dimensiones del panel, pero
     * conservando la relación de aspecto.<br>
     * &nbsp;&nbsp;&nbsp;RELLENAR, ajusta el tamaño de la imagen al tamaño del panel.
     */
    public static enum Ajuste {

        ORIGINAL, AJUSTAR, RELLENAR
    };

    /**
     * Establece la posición de la imagen respecto al fondo:<br>
     * &nbsp;&nbsp;&nbsp;ESQUINA, establece que la imagen se dibujará en la
     * esquiina superior izquierda del panel.<br>
     * &nbsp;&nbsp;&nbsp;CENTRADA, establece que la imagen se centrára en el panel.
     */
    public static enum Posicion {

        ESQUINA, CENTRADA
    };
    private Image imagen = null;
    private int anchoImagen = -1;
    private int altoImagen = -1;
    private Ajuste modo = Ajuste.ORIGINAL;
    private Posicion posicion = Posicion.ESQUINA;

    /**
     * Retorna el modo en que se ajusta la imagen al tamaño del panel.
     * @return El valor retornado puede compararse con uno de los valores de la
     * enumeración <b>PictureBox.Ajuste</b>.
     */
    public Ajuste getModo() {
        return modo;
    }

    /**
     * Asigna el modo en que se ajusta la imagen al tamaño del panel. Puede utilizarse
     * una de los valores de la enumeración <b>PictureBox.Ajuste</b>
     * @param Uno de los valres de la enumeración <b>PictureBox.Ajuste</b>.
     */
    public void setModo(Ajuste modo) {
        this.modo = modo == null ? Ajuste.ORIGINAL : modo;
        this.validate();
    }

    /**
     * Retorna el valor de posición de la imagen respecto al panel.
     * @return El valor retornado puede compararse con uno de los valores de la
     * enumeración <b>PictureBox.Posicion</b>.
     */
    public Posicion getPosicion() {
        return posicion;
    }

    /**
     * Establece el valor de posición de la imagen respecto al panel.
     * @param Uno de los valores de la enumeración <b>PictureBox.Posicion</b>.
     */
    public void setPosicion(Posicion posicion) {
        this.posicion = posicion == null ? Posicion.ESQUINA : posicion;
        this.validate();
    }

    /**
     * Retorna la imagen de fondo.
     * @return La imagen de fondo o null si no ha sido establecida.
     */
    public Image geImagen() {
        return imagen;
    }

    /**
     * Establece la imagen de fondo provocando la renderización del panel.
     * @param La imagen de fondo.
     */
    public void setImagen(Image fondo) {
        this.imagen = fondo;
        if (fondo != null) {
            this.anchoImagen = fondo.getWidth(this);
            this.altoImagen = fondo.getHeight(this);
        }
        this.validate(); // provoca que se repinte el componente
    }

    /**
     * Establece la imagen de fondo provocando la renderización del panel.
     * @param La ruta absoluta del archivo con la imagen de fondo.
     */
    public void setRutaImagen(File archivo) {
        setRutaImagen(archivo == null ? (String) null : archivo.getAbsolutePath());
    }

    /**
     * Establece la imagen de fondo provocando la renderización del panel.
     * @param La ruta absoluta del archivo con la imagen de fondo.
     */
    public void setRutaImagen(String ruta) {
        Image img = null;
        try {
            img = java.awt.Toolkit.getDefaultToolkit().createImage(ruta);
            //img = javax.imageio.ImageIO.read(PictureBox.class.getClassLoader().getResourceAsStream(ruta));
        } catch (Exception ex) {
        }
        setImagen(img);
    }

    @Override
    public void paint(Graphics g) {
        if (imagen != null) {
            int ancho;
            int alto;
            // SE ESTABLE EL ANCHO Y ALTO
            if (anchoImagen < 0) {
                anchoImagen = imagen.getWidth(this);
            }
            if (altoImagen < 0) {
                altoImagen = imagen.getHeight(this);
            }
            int wPanel = this.getWidth();
            int hPanel = this.getHeight();
            double pendientePanel = (double) wPanel / (double) hPanel;
            double pendienteImagen = (double) anchoImagen / (double) altoImagen;
            switch (getModo()) {
                case AJUSTAR:
                    if (pendienteImagen > pendientePanel) {
                        ancho = wPanel;
                        alto = (int) (wPanel / pendienteImagen);
                    } else {
                        alto = hPanel;
                        ancho = (int) (hPanel * pendienteImagen);
                    }
                    break;
                case RELLENAR:
                    ancho = wPanel;
                    alto = hPanel;
                    break;
                default: // ORIGINAL
                    ancho = anchoImagen;
                    alto = altoImagen;
                    break;
            }
            // SE ESTABLE LA POSICIÓN
            int posX = 0;
            int posY = 0;
            if (posicion == Posicion.CENTRADA) {
                if (ancho != wPanel) {
                    posX = (wPanel - ancho) >> 1;  // se divide por 2
                }
                if (alto != hPanel) {
                    posY = (hPanel - alto) >> 1;   // se divide por 2
                }
            }
            // SE DIBUJA LA IMAGEN
            g.drawImage(imagen, posX, posY, ancho, alto, this);
        }
    }
}
