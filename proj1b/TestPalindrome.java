import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindromeSpaceWord() {
        assertFalse(palindrome.isPalindrome("aba "));
        assertTrue(palindrome.isPalindrome("aba"));
    }

    @Test
    public void testIsPalindromeOneWord() {
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome("B"));
    }

    @Test
    public void testIsPalindromeTwoWords() {
        assertTrue(palindrome.isPalindrome("aa"));
        assertFalse(palindrome.isPalindrome("ab"));
        assertFalse(palindrome.isPalindrome("aA"));
    }

    @Test
    public void testIsPalindromeThreeWords() {
        assertTrue(palindrome.isPalindrome("aba"));
        assertFalse(palindrome.isPalindrome("cat"));
        assertFalse(palindrome.isPalindrome("abA"));
    }

    @Test
    public void testIsPalindromeCCZeroAndOneWord() {
        CharacterComparator cc = new OffByOne();
        assertTrue(palindrome.isPalindrome(" ", cc));
        assertTrue(palindrome.isPalindrome("a", cc));
    }

    @Test
    public void testIsPalindromeCCWords() {
        CharacterComparator cc = new OffByOne();
        assertTrue(palindrome.isPalindrome("flake", cc));
        assertTrue(palindrome.isPalindrome("cad", cc));
        assertFalse(palindrome.isPalindrome("jadslasd", cc));
    }
}
