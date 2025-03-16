import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    static CharacterComparator offBy5 = new OffByN(5);
    static CharacterComparator offBy1 = new OffByN(1);

    @Test
    public void testEqualCharsFiveTrue() {
        assertTrue(offBy5.equalChars('a', 'f'));
        assertTrue(offBy5.equalChars('f', 'a'));
    }

    @Test
    public void testEqualCharsFiveFalse() {
        assertFalse(offBy5.equalChars('h', 'f'));
    }

    @Test
    public void testEqualCharsOneTrue() {
        assertTrue(offBy1.equalChars('A', 'b'));
        assertTrue(offBy1.equalChars('R', 'q'));
    }
}
