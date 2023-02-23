package fr.projet.pong;
import java.awt.Font;

public class Constants {

    public static Font FONT = new Font("system", Font.BOLD, 20);

    public static int WINDOW_WIDTH = 1200;

    public static int WINDOW_HEIGHT = (int) (WINDOW_WIDTH * (5.0/9.0));

    public static int BALL_RADIUS = 20;

    public static final int FPS_SET = 120;

	public static final int UPS_SET = 200;

    public static final int Z_KEY = 90;

    public static final int S_KEY = 83;

    public static final int UP_KEY = 38;

    public static final int DOWN_KEY = 40;

    public static final int PLAYER_SPEED = 3;

    public static final int PLAYER_WIDTH = 10;

    public static final int PLAYER_HEIGHT = 100;

    public static boolean PLAYER1_UP = false;

    public static boolean PLAYER1_DOWN = false;

    public static boolean PLAYER2_UP = false;

    public static boolean PLAYER2_DOWN = false;
}
