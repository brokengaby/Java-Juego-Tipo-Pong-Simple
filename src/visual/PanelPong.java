package visual;

import clases.Barra;
import clases.Pelota;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.util.TimerTask;
import javax.swing.JPanel;
import java.util.Timer;

public class PanelPong extends JPanel {

    //Para reiniciar al perder
    MouseListener ml;

    //Para mover la barra
    KeyListener kl;

    //Pelota
    Pelota p;

    //Barra
    Barra b;

    //Fuente
    Font fuente_puntuacion = new Font("consolas", 1, 15);
    Font fuente_perdiste = new Font("consolas", 1, 25);

    //Timer para repintar las cosas
    Timer timer;
    TimerTask task;

    //Pointer infor
    Point pointer;

    Color color_barra, color_pelota, color_fondo;

    //Constructor
    //Por defecto, del mismo tamaño
    public PanelPong() {
        //Tamaño
        this.setSize(550, 400);
        //Esquema de color
        this.setEsquemaColor();
        //Color de fondo
        this.setBackground(color_fondo);
        //Focusable
        this.setFocusable(true);
        //Doble buffer para el dibujado
        this.setDoubleBuffered(true);
        //Hacemos la barra
        b = new Barra(this, 15, 15, 100);
        //Hacemos la pelota
        p = new Pelota(this, 45, 45, b);
        //Corremos
        this.defineTimer();
        //Añadimos el control para reiniciar
        this.defineMouseListener(this);
        //Añadimos el control para mover la barra
        //this.defineKeyListener(this);
    }

    //Dibujando
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //Ponemos sus colores
        g.setColor(color_pelota);
        //Dibujando la raya divisora en medio
        g.drawLine(this.getWidth() / 2, 0, this.getWidth() / 2, this.getHeight());
        //Dibujado la pelota, un rectángulo
        g.setColor(color_pelota);
        g.fillRect(p.getX(), p.getY(), p.getWidth(), p.getWidth());
        //Dibujamos la barra, otro rectángulo
        g.setColor(color_barra);
        g.fillRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
        //Dibujamos la puntuacion
        g.setColor(Color.WHITE);
        g.setFont(fuente_puntuacion);
        if (p.isJugando()) {
            g.drawString("Puntuación: " + p.getPuntuacion(), getWidth() - 125, 17);
        }
        //SI HEMOS PERDIDO}
        else{
            g.setFont(fuente_perdiste);
            g.drawString("¡Perdiste!, Puntuación: " + p.getPuntuacion(), 100, this.getHeight() / 2 - 25);
            g.drawString("¡haz CLICK IZQ. para reiniciar!", 60, this.getHeight() / 2 + 20);
        }
    }

    //Repintando constantement con el timer
    private void defineTimer() {
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                //Repintamos
                repaint();
                pointer = getMousePosition();
                if (pointer != null) {

                    //Aquí ha alcanzo los limites del desplazamiento de la barra
                    if (pointer.y + b.getHeight() / 2 > getHeight() || pointer.y < b.getHeight() / 2) {

                    } //Aquí si se puede mover
                    else {
                        b.setY(pointer.y - b.getHeight() / 2);
                    }

                }

                if (p.isAlcanza_limite()) {
                    setEsquemaColor();
                    setBackground(color_fondo);
                    //Si alcanza cierta puntuacion, disminuye el tamaño de la barra en vez del de la pelota
                    if(p.getWidth() < 35){
                        b.disminuirTamanio();
                    }      
                }
            }
        };
        //Agendamos
        timer.schedule(task, 1000, 1);
    }

    private void defineMouseListener(JPanel panel) {
        ml = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                //Si ya no estamos jugando reiniciamos
                if (!p.isJugando()) {
                    p = new Pelota(panel, 45, 45, b);
                    b.setHeight(100);
                    setEsquemaColor();
                    setBackground(color_fondo);
                } else {

                }
            }

            @Override
            public void mousePressed(MouseEvent me) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.4
            }

            @Override
            public void mouseExited(MouseEvent me) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        //Poniendolo
        this.addMouseListener(ml);
    }

    private void setEsquemaColor() {
        int x = (int) (Math.random() * 5);
        if (x == 0) {
            color_fondo = new Color(5, 7, 34);
            color_barra = new Color(77, 255, 255);
            color_pelota = new Color(24, 48, 211);
        }
        if (x == 1) {
            color_fondo = new Color(84, 0, 122);
            color_barra = new Color(162, 221, 75);
            color_pelota = new Color(215, 146, 117);
        }
        if (x == 2) {
            color_fondo = new Color(101, 0, 65);
            color_barra = new Color(130, 4, 228);
            color_pelota = new Color(211, 252, 0);
        }
        if (x == 3) {
            color_fondo = new Color(13, 32, 77);
            color_barra = new Color(111, 255, 255);
            color_pelota = new Color(207, 0, 99);
        }
        if (x == 4) {
            color_fondo = new Color(50, 1, 69);
            color_barra = new Color(127, 2, 251);
            color_pelota = new Color(176, 245, 86);
        }
    }
}
