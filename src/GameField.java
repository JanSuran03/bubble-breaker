import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameField extends Canvas {

    public Game game;

    static public final int restart_button_x = 300,
            restart_button_y = 20,
            restart_button_width = 170,
            restart_button_height = 44,
            restart_button_radius = 16;

    static public boolean onRestartButton(int mouse_x, int mouse_y) {
        return mouse_x >= restart_button_x
                && mouse_x <= restart_button_x + restart_button_width
                && mouse_y >= restart_button_y
                && mouse_y <= restart_button_y + restart_button_height;
    }

    static public void paintRestartButton(Graphics g) {
        // inner
        g.setColor(Color.WHITE);
        g.fillRoundRect(restart_button_x, restart_button_y,
                restart_button_width, restart_button_height,
                restart_button_radius, restart_button_radius);
        // border
        g.setColor(Color.BLACK);
        g.drawRoundRect(restart_button_x, restart_button_y,
                restart_button_width, restart_button_height,
                restart_button_radius, restart_button_radius);
        // text
        g.setFont(new Font("Calibri", Font.PLAIN, 30));
        g.drawString("RESTART", restart_button_x + 15, restart_button_y + 35);
    }

    public GameField() {
        game = new Game();
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
        int raw_x = me.getX(), raw_y = me.getY();
        int x = raw_x - offset_x;
        int y = raw_y - offset_y;
        if (onRestartButton(raw_x, raw_y)) {
            restartGame();
        } else if (x >= 0 && y >= 0) {
            int col = x / cell_size;
            int row = y / cell_size;
            if (row < Game.height && col < Game.width
                    && game.gameMove(row, col))
                repaint();
        }
    }

    static public int cell_size = 50, r = cell_size,
            offset_x = cell_size, offset_y = cell_size * 2;

    public void paintCells(Graphics g) {
        for (int row = 0; row < Game.height; row++)
            for (int col = 0; col < Game.width; col++) {
                Cell cell = game.field[row][col];
                if (cell != null) {
                    Color c = Cell.color_to_awt.get(
                            game.field[row][col].cell_color);
                    boolean nothing_selected = game.selected.isEmpty();
                    if (nothing_selected || cell.is_selected) {
                        g.setColor(c);
                    } else {
                        g.setColor(new Color(c.getRed(), c.getGreen(), c.getBlue(),
                                /* color alpha in percent */ 255 * 90 / 100));
                    }
                    g.fillRoundRect(
                            (col + 1) * cell_size + 1,
                            (row + 2) * cell_size + 1,
                            cell_size - 2, cell_size - 2,
                            r, r);
                }
            }
    }

    public void restartGame() {
        game = new Game();
        repaint();
    }

    public void drawScore(Graphics g) {
        g.setFont(new Font("Calibri", Font.PLAIN, 40));
        g.drawString("SCORE: " + game.score, 20, 60);
    }

    @Override
    public void paint(Graphics g) {
        drawScore(g);
        this.setBackground(new Color(188, 188, 188));
        paintCells(g);
        paintRestartButton(g);
    }
}
