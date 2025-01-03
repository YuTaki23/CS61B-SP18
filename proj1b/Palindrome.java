public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> items = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            items.addLast(ch);
        }
        return items;
    }

    public boolean isPalindrome(String word) {
        if (word.length() == 0 || word.length() == 1) {
            return true;
        }

        Deque<Character> items;
        items = wordToDeque(word);
        for (int i = 0; i < word.length() / 2; i++) {
            char first = items.removeFirst();
            char last = items.removeLast();
            if (first != last) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.length() == 0 || word.length() == 1) {
            return true;
        }

        Deque<Character> items;
        items = wordToDeque(word);
        for (int i = 0; i < word.length() / 2; i++) {
            char first = items.removeFirst();
            char last = items.removeLast();

            if (first != last && !cc.equalChars(first, last)) {
                return false;
            }
        }
        return true;
    }
}
