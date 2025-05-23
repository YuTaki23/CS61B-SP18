import java.util.*;

/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        // TODO: Implement LSD Sort
        if (asciis == null || asciis.length <= 1) {
            return asciis;
        }
        String[] res = Arrays.copyOf(asciis, asciis.length);

        int maxStringLength = 0;
        for (String ascii : res) {
            if (ascii.length() > maxStringLength) {
                maxStringLength = ascii.length();
            }
        }

        for (int j = maxStringLength - 1; j >= 0; j--) {
            sortHelperLSD(res, j);
        }
        return res;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort
        ArrayList<String>[] bucket_ = new ArrayList[256];
        for (int i = 0; i < 256; i++) {
            bucket_[i] = new ArrayList<>();
        }

        for (String ascii : asciis) {
            int key = 0;
            if (index < ascii.length()) {
                key = ascii.charAt(index);
            }
            bucket_[key].add(ascii);
        }

        int i = 0;
        for (ArrayList<String> bucket : bucket_) {
            for (String s : bucket) {
                asciis[i++] = s;
            }
        }
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }
}
