import javax.swing.text.Style;
import java.awt.*;

public class GameField extends Canvas {

    public Game game;

    public GameField() {
        game = new Game();
        game.init();
    }

    static public int cell_size = 50, r = 10;

    public void paintCells(Graphics g) {
        for (int row = 0; row < Game.height; row++)
            for (int col = 0; col < Game.width; col++) {
                Color color = Cell.color_to_awt.get(
                        game.field[row][col].cell_color);
                g.setColor(color);
                g.fillRoundRect(
                        (col + 1) * cell_size + 1,
                        (row + 2) * cell_size + 1,
                        cell_size - 2, cell_size - 2,
                        r, r);
            }
    }

    @Override
    public void paint(Graphics g) {
        g.setFont(new Font("Calibri", Font.PLAIN, 40));
        g.drawString("SCORE: " + game.score, 20, 60);
        this.setBackground(new Color(188, 188, 188));
        paintCells(g);
    }
}
