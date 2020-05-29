package homework.eight;

public class Main {

    private static final int ARRAY_LENGTH_X = 5;
    private static final int ARRAY_LENGTH_Y = 5;

    private static int[][] array;
    private static int number;

    public static void main(String[] args) {
        array = new int[ARRAY_LENGTH_X][ARRAY_LENGTH_Y];
        doFillArray(0, 0, ARRAY_LENGTH_X, ARRAY_LENGTH_Y);
        for (int[] ints : array) {
            for (int anInt : ints) {
                System.out.printf("[%d]", anInt);
            }
            System.out.println();
        }
    }

    private static void doFillArray(int startX, int startY, int endX, int endY) {
        if ((startX == endX - 1) || (startY == endY - 1)) {
            array[startX][startY] = ++number;
        } else if ((startX - endX - 1) > 0 && (startY - endY - 1) > 0) {
            return;
        } else {
            for (int i = startY; i < endY - 1; i++) {
                array[startX][i] = ++number;
            }
            for (int i = startX; i < endX - 1; i++) {
                array[i][endY - 1] = ++number;
            }
            for (int i = endY - 1; i > startY; i--) {
                array[endX - 1][i] = ++number;
            }
            for (int i = endX - 1; i > startX; i--) {
                array[i][startY] = ++number;
            }
            doFillArray(startX + 1, startY + 1, endX - 1, endY - 1);
        }
    }
}
