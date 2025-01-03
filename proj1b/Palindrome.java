public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> items = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            items.addLast(ch);
        }
        return items;
    }
}
