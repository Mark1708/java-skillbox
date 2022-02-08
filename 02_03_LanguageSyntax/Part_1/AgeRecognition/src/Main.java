public class Main {

    public static void main(String[] args) {

        int vasyaAge = 29;
        int katyaAge = 14;
        int mishaAge = 36;

        int min = -1, middle = -1, max = -1;

        if (vasyaAge >= katyaAge && katyaAge >= mishaAge && vasyaAge >= mishaAge) {
            max = vasyaAge;
            middle = katyaAge;
            min = mishaAge;
        } else if (vasyaAge >= katyaAge && katyaAge <= mishaAge && vasyaAge >= mishaAge) {
            max = vasyaAge;
            middle = mishaAge;
            min = katyaAge;
        } else if (vasyaAge <= katyaAge && katyaAge >= mishaAge && vasyaAge >= mishaAge) {
            max = katyaAge;
            middle = vasyaAge;
            min = mishaAge;
        } else if (vasyaAge <= katyaAge && katyaAge >= mishaAge && vasyaAge <= mishaAge) {
            max = katyaAge;
            middle = mishaAge;
            min = vasyaAge;
        } else if (vasyaAge <= katyaAge && katyaAge <= mishaAge && vasyaAge <= mishaAge) {
            max = mishaAge;
            middle = katyaAge;
            min = vasyaAge;
        } else {
            max = mishaAge;
            middle = vasyaAge;
            min = katyaAge;
        }

        System.out.printf(
                "Minimal age: %d\nMiddle age: %d\nMaximum age: %d\n", min, middle, max
        );
    }
}
