public class OffByN implements CharacterComparator {
    private int items;

    public OffByN(int N) {
        this.items = N;
    }

    public boolean equalChars(char x, char y) {
        x = Character.toLowerCase(x);
        y = Character.toLowerCase(y);
        int diff = Math.abs(x - y);
        return diff == this.items;
    }
}
