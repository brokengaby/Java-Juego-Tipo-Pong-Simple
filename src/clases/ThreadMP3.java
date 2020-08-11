package clases;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class ThreadMP3 extends Thread {

    Player player;
    public static boolean reproduciendo = false;
    public static boolean pausa = false;

    @Override
    public void run() {
        reproduciendo = true;
        try {
            while(reproduciendo){
                player.play(1);
            }
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
    }

    public void setMP3Player(Player player) {
        this.player = player;
    }

}
