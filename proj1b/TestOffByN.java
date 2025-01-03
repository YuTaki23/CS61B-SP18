import org.junit.Test;
import static org.junit.Assert.*;


public class TestOffByN {
    static CharacterComparator obo2 = new OffByN(2);
    static CharacterComparator obo3 = new OffByN(3);

    @Test
    public void testEqualCharsByTwo() {
        assertTrue(obo2.equalChars('a', 'c'));
        assertFalse(obo2.equalChars('a', 'A'));
    }

    @Test
    public void  testEqualCharsByThree() {
        assertTrue(obo3.equalChars('a', 'd'));
        assertFalse(obo3.equalChars('a', 'A'));
    }
}
