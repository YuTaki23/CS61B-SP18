import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testEqualCharsOneLetter() {
        OffByOne obo = new OffByOne();
        assertTrue(obo.equalChars('a', 'b'));
        assertFalse(obo.equalChars('C', 'F'));
        assertFalse(obo.equalChars('B', 'D'));
        assertFalse(obo.equalChars('A','C'));
        assertTrue(obo.equalChars('r', 'q'));
    }

    @Test
    public void testEqualCharsOneSign() {
        OffByOne obo = new OffByOne();
        assertTrue(obo.equalChars('%', '&'));
    }
    //Uncomment this class once you've created your CharacterComparator interface and OffByOne class. **/
}
