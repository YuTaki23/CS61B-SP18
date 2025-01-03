public class OffByN implements CharacterComparator{
    public int result;

    public OffByN(int N) {
        this.result = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        int diff = x - y;
        return Math.abs(diff) == result;
    }
}
