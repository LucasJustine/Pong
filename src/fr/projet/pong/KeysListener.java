package fr.projet.pong;
import java.awt.event.*;

public class KeysListener implements KeyListener{

    private Game game;

    public KeysListener(Game game) {
        this.game = game;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        this.game.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.game.keyReleased(e);
    }
    
}
