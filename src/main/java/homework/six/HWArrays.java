package homework.six;

public class HWArrays {

    public int[] getNewArray(int[] array) {
        int position = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 4){
                position = i;
            }
        }
        if (position == -1){
            throw new RuntimeException();
        }
        int[] resultArray = new int[array.length - (position + 1)];
        System.arraycopy(array, position + 1, resultArray, 0, array.length - (position + 1));
        return resultArray;
    }

    public Boolean hasOneOrFourIntoArray(int[] array){
        for (int value : array) {
            if (value != 1 && value != 4) {
                return false;
            }
        }
        int count = 0;
        for (int value : array) {
            if (value == 1) {
                count++;
            }
        }
        return count != 0 && count != array.length;
    }

}
