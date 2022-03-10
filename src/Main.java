import javax.swing.*;
import java.util.Arrays;
import java.util.Random;

public class Main {

    static public void testCluster(Game game) {
        game.print();
        System.out.println(5 + " " + 15 + " " + game.field[5][15].cell_color);
        for (Integer i : Cell.colorCluster(game, 5, 15))
            System.out.print(Arrays.toString(Cell.toCoords(i)) + ' ');
    }

    static public void removeRandom(Game game, int n) {
        Random random = new Random();
        int border = Game.width * Game.height;
        for (int i = 0; i < n; i++) {
            int[] coords = Cell.toCoords(random.nextInt(border));
            game.field[coords[0]][coords[1]] = null;
        }
    }

    static public void clear() {
        System.out.println('\n');
    }

    static public void main(String[] args) {
        JFrame jf = new JFrame("Bubble breaker");
        GameField game_field = new GameField();
        jf.add(game_field);
        jf.setBounds(100, 100, 1200, 800);
        jf.setVisible(true);
    }
}
