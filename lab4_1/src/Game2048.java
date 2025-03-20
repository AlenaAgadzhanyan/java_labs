import java.awt.*;
import javax.swing.*;

public class Game2048 extends JPanel {

    private static final Color BG_COLOR = new Color(0xbbada0);
    private static final String FONT_NAME = "Arial";
    private static final int TILE_SIZE = 64;
    private static final int TILE_MARGIN = 16;

    private int[][] board;
    private int size = 4;
    private int score = 0;

    public Game2048() {
        resetGame(); // Initialize the game board
    }


    private void resetGame() {
        board = new int[size][size];
        score = 0;
        repaint(); // Redraw the board
    }
    private Color getTileColor(int value) {
        switch (value) {
            case 2: return new Color(0xeee4da);
            case 4: return new Color(0xede0c8);
            case 8: return new Color(0xf2b179);
            case 16: return new Color(0xf59563);
            case 32: return new Color(0xf67c5f);
            case 64: return new Color(0xf65e3b);
            case 128: return new Color(0xedcf72);
            case 256: return new Color(0xedcc61);
            case 512: return new Color(0xedc850);
            case 1024: return new Color(0xedc53f);
            case 2048: return new Color(0xedc22e);
            default: return new Color(0xcdc1b4);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(BG_COLOR);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                int value = board[row][col];

                int x = col * (TILE_SIZE + TILE_MARGIN) + TILE_MARGIN - 5;
                int y = row * (TILE_SIZE + TILE_MARGIN) + TILE_MARGIN;

                g.setColor(getTileColor(value));
                g.fillRoundRect(x, y, TILE_SIZE, TILE_SIZE, 14, 14);
                
            }
        }

        g.setFont(new Font(FONT_NAME, Font.BOLD, 24));
        g.setColor(new Color(0x776e65));
        g.drawString("Score: " + score, TILE_MARGIN, this.getHeight() - 15);
    }

    public static void main(String[] args) {
        JFrame game = new JFrame();
        game.setTitle("2048 Game");
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setSize(340, 400);
        game.setResizable(false);

        game.add(new Game2048());

        game.setLocationRelativeTo(null); // Center on screen
        game.setVisible(true);
    }
}