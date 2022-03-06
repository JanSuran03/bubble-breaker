public class Field {
    static public final int height = 10, width = 20;
    public Cell[][] field;

    public Field() {
        field = new Cell[height][width];
    }

    public void init() {
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                field[i][j] = new Cell();
    }

    public void print() {
        System.out.print("   ");
        for (int i = 0; i < Field.width; i++)
            System.out.printf("%3d", i);
        System.out.print('\n');
        int r = 0;
        for (Cell[] row : field) {
            System.out.printf("%3d", r++);
            for (Cell cell : row) {
                System.out.printf("%3s",
                        cell == null ? " " : Cell.first_letter(cell.cell_color));
            }
            System.out.print('\n');
        }
    }

    public void moveDown() {
        for (int col = 0; col < width; col++) {
            int bottom_row = height - 1;
            boolean behind = false;
            for (int row = bottom_row; row >= 0; row--) {
                if (field[row][col] == null) {
                    behind = true;
                } else {
                    if (behind) {
                        field[bottom_row][col] = field[row][col];
                        field[row][col] = null;
                    }
                    bottom_row--;
                }
            }
        }
    }

    public void moveLeft() {
        boolean[] empty_rows = new boolean[width];
        int number_of_not_empty = width;
        for (int i = 0; i < width; i++) {
            empty_rows[i] = false;
        }
        for (int col = 0; col < width; col++) {
            boolean empty = true;
            for (int row = 0; row < height; row++)
                if (field[row][col] != null) {
                    empty = false;
                    break;
                }
            if (empty) {
                empty_rows[col] = true;
                number_of_not_empty--;
            }
        }
        // I know I need to fill this number of columns
        for (int i = 0; i < number_of_not_empty; i++) {
            // do we need to find a not-empty column?
            if (empty_rows[i]) {
                // let's find a not-empty column
                for (int seek = i + 1; seek < width; seek++) {
                    // if the column is not empty
                    if (!empty_rows[seek]) {
                        // shift the column to where we need it
                        for (int row = 0; row < height; row++) {
                            field[row][i] = field[row][seek];
                            field[row][seek] = null;
                        }
                        // and mark it as empty
                        empty_rows[seek] = true;
                        break;
                    }
                }
            }
        }
    }


}
