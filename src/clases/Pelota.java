package clases;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

public class Pelota extends Thread {

    //Atributos
    private int limite_x, limite_y, incremento_x = 1, incremento_y = 1, x = 0, y = 0, width, height, puntuacion = 0;

    Barra b;

    boolean jugando = true, alcanza_limite = false;

    //Constructor
    public Pelota(JPanel marco, int width, int height, Barra b) {
        //Su barra
        this.b = b;
        //Estableciendo los limites
        this.limite_x = marco.getWidth();
        this.limite_y = marco.getHeight();
        //Estableciendo tamaño
        this.width = width;
        this.height = height;
        //Estableciendo posicion inicial al azar entre el panel
        x = 60; //Siempre 30
        y = (int) (Math.random() * 50) + height;
        //Iniciamos
        this.start();
    }

    //Método run
    @Override
    public void run() {
        while (true) {

            alcanza_limite = false;

            if (x <= -width) {
                jugando = false;
                break;
            }

            if ((x + width) == limite_x) {
                incremento_x *= -1;
                puntuacion++;
                alcanza_limite = true;

                //Disminuimos tamaño
                if (puntuacion > 0 && puntuacion % 5 == 0) {
                    this.disminuirTamanio();
                }
            }

            if (golpeaIzquierda()) {
                incremento_x *= -1;
            }

            if (y == 0 || (y + height) == limite_y) {
                incremento_y *= -1;
            }

            x += incremento_x;
            y += incremento_y;

            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(Pelota.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //Métodos get/set
    public int getLimite_x() {
        return limite_x;
    }

    public void setLimite_x(int limite_x) {
        this.limite_x = limite_x;
    }

    public int getLimite_y() {
        return limite_y;
    }

    public void setLimite_y(int limite_y) {
        this.limite_y = limite_y;
    }

    public int getIncremento_x() {
        return incremento_x;
    }

    public void setIncremento_x(int incremento_x) {
        this.incremento_x = incremento_x;
    }

    public int getIncremento_y() {
        return incremento_y;
    }

    public void setIncremento_y(int incremento_y) {
        this.incremento_y = incremento_y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isJugando() {
        return jugando;
    }

    public void setJugando(boolean jugando) {
        this.jugando = jugando;
    }

    private boolean golpeaIzquierda() {
        if (getX() == b.getX() + b.getWidth()) {
            if (getY() >= b.getY() && getY() <= b.getY() + b.getHeight()) {
                return true;
            } else if (getY() + getHeight() >= b.getY() && getY() + getHeight() <= b.getY() + b.getHeight()) {
                return true;
            }
        }
        return false;
    }

    public boolean isAlcanza_limite() {
        return alcanza_limite;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    private void disminuirTamanio() {
        if (width > 5) {
            width -= 5;
            height -= 5;
        }
    }

}
