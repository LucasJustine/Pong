package fr.projet.pong;
import javax.swing.JFrame;
import java.awt.event.*;

public class Main {

    public static void main(String[] args) {
        JFrame jf = new JFrame();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Game dessin= new Game();
        jf.add(dessin);
        jf.pack();
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
    }
}

