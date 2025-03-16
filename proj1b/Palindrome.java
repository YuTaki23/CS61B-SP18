public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            char currentWord = word.charAt(i);
            deque.addLast(currentWord);
        }
        return deque;
    }

    public boolean isPalindrome(String word) {
        int length = word.length();
        if (length != 0 && length != 1) {
            Deque<Character> deque = wordToDeque(word);
            for (int i = 0, j = deque.size() - 1; i < deque.size() / 2; i++, j--) {
                if (deque.get(i) != deque.get(j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        int length = word.length();
        if (length != 0 && length != 1) {
            Deque<Character> deque = wordToDeque(word);
            for (int i = 0, j = deque.size() - 1; i < deque.size() / 2; i++, j--) {
                if (!cc.equalChars(word.charAt(i), word.charAt(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
