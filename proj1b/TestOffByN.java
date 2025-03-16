import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    static CharacterComparator offBy5 = new OffByN(5);

    @Test
    public void testEqualCharsFiveTrue() {
        assertTrue(offBy5.equalChars('a', 'f'));
        assertTrue(offBy5.equalChars('f', 'a'));
    }

    @Test
    public void testEqualCharsFiveFalse() {
        assertFalse(offBy5.equalChars('h', 'f'));
    }
}
