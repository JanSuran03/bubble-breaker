import javax.swing.text.Style;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameField extends Canvas {

    public Game game;

    public GameField() {
        game = new Game();
        game.init();
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                dispatchMouseReleased(me);
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
        });
    }

    public void dispatchMouseReleased(MouseEvent me) {
        int x = me.getX() - offset_x;
        int y = me.getY() - offset_y;
        if (x >= 0 && y >= 0) {
            int col = x / cell_size;
            int row = y / cell_size;
            if (row < Game.height && col < Game.width) {
                game.gameMove(row, col);
            }
        }
        repaint();
    }

    static public int cell_size = 50, r = 10,
            offset_x = cell_size, offset_y = cell_size * 2;

    public void paintCells(Graphics g) {
        for (int row = 0; row < Game.height; row++)
            for (int col = 0; col < Game.width; col++) {
                if (game.field[row][col] != null) {
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
    }

    @Override
    public void paint(Graphics g) {
        g.setFont(new Font("Calibri", Font.PLAIN, 40));
        g.drawString("SCORE: " + game.score, 20, 60);
        this.setBackground(new Color(188, 188, 188));
        paintCells(g);
    }
}
