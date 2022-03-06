import javax.swing.*;
import java.util.Arrays;
import java.util.Random;
import java.util.Vector;

public class Main {

    static public void testCluster(Field game) {
        game.print();
        System.out.println(5 + " " + 15 + " " + game.field[5][15].cell_color);
        for (Integer i : Cell.colorCluster(game, 5, 15))
            System.out.print(Arrays.toString(Cell.toCoords(i)) + ' ');
    }

    static public void removeRandom(Field game, int n) {
        Random random = new Random();
        int border = Field.width * Field.height;
        for (int i = 0; i < n; i++) {
            int[] coords = Cell.toCoords(random.nextInt(border));
            game.field[coords[0]][coords[1]] = null;
        }
    }

    static public void clear(){
        System.out.println('\n');
    }

    public static void main(String[] args) {
        Field game = new Field();
        game.init();
        //testCluster(game);
        game.print();
        removeRandom(game, 350);
        clear();
        game.print();
        game.moveLeft();
        clear();
        game.print();
    }
}
