package clases;

import javax.swing.JPanel;

public class Barra {

    //Atributos
    private int x, y, width, height, limite_y, incremento_y = 30;

    //Constructor
    public Barra(JPanel marco, int x, int width, int height) {
        this.x = x;
        this.width = width;
        this.height = height;
        //Centramos en y
        this.y = (marco.getHeight() - height) / 2;
    }

    //Métodos get/set
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getLimite_y() {
        return limite_y;
    }

    public void setY(int y) {
        this.y = y;
    }

    //Método para subir o bajar
    public void irArriba() {
        y -= incremento_y;
    }

    public void irAbajo() {
        y += incremento_y;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void disminuirTamanio() {
        if (height > 10) {
            height -= 5;
        }
    }
}
