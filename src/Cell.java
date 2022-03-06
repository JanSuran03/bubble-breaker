import java.util.*;
import java.util.function.Predicate;

public class Cell {
    public enum CellColor {
        Blue,
        Green,
        Red,
        Yellow,
    }

    public CellColor cell_color;

    public Cell() {
        this.cell_color = random_color();
    }

    static public char first_letter(CellColor color) {
        return color.toString().charAt(0);
    }

    static public final CellColor[] colors = CellColor.values();
    static public final int num_colors = colors.length;
    static public final Random random = new Random();


    static public CellColor random_color() {
        return colors[random.nextInt(num_colors)];
    }

    static public int toIndex(int row, int col) {
        return row * Game.width + col;
    }

    static public final int h = Game.height, w = Game.width;

    static public int[] toCoords(int as_index) {
        return new int[]{as_index / w, as_index % w};
    }

    static public Vector<Integer> cellNeighbors(int[] coords) {
        int row = coords[0], col = coords[1];
        int idx = toIndex(row, col);
        Vector<Integer> neighbors;
        switch (col) {
            case 0:
                neighbors = new Vector<>(List.of(new Integer[]{idx - w, idx + 1, idx + w}));
                break;
            case w - 1:
                neighbors = new Vector<>(List.of(new Integer[]{idx - w, idx - 1, idx + w}));
                break;
            default:
                neighbors = new Vector<>(List.of(new Integer[]{idx - w, idx - 1, idx + 1, idx + w}));
                break;
        }
        Predicate<Integer> in_field = i -> (i < 0 || i >= w * h);
        neighbors.removeIf(in_field);
        return neighbors;
    }

    static public Vector<Integer> colorCluster(Game game, int row, int col) {
        Cell[][] field = game.field;
        CellColor color = field[row][col].cell_color;
        int idx = toIndex(row, col);
        Stack<Integer> color_cluster = new Stack<>();
        Stack<Integer> pending = new Stack<>();
        pending.add(idx);
        HashSet<Integer> gone = new HashSet<>();
        while (!pending.isEmpty()) {
            int current = pending.pop();
            Vector<Integer> neighbors = cellNeighbors(toCoords(current));
            for (Integer neighbor : neighbors) {
                int[] coords = toCoords(neighbor);
                if (!gone.contains(neighbor) && field[coords[0]][coords[1]].cell_color == color) {
                    pending.add(neighbor);
                    color_cluster.add(neighbor);
                }
                gone.add(neighbor);
            }
        }
        if (!gone.contains(idx))
            color_cluster.add(idx);
        return color_cluster;
    }
}
