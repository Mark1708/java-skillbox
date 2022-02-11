import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        char[] array = {'a', 'b', 'c', 'd', 's'};
        System.out.println(Arrays.toString(reverse(array)));
    }

    private static char[] reverse(char[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            char copy = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = copy;
        }
        return array;
    }
}
