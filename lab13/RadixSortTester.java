import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Arrays;

public class RadixSortTester {

    // 测试用例设计
    private static String[] emptyArray = {}; // 空数组
    private static String[] singleElement = {"hello"}; // 单元素数组
    private static String[] sameLength = {"dog", "cat", "apple", "banana"}; // 等长但无序
    private static String[] variableLength = {"a", "abc", "ab", "", "abcd"}; // 变长含空串
    private static String[] specialCharacters = {"a!", "a1", "a0", "a", "a#"}; // 特殊字符
    private static String[] caseSensitive = {"abc", "ABC", "aBc"}; // 大小写敏感测试

    /**
     * 验证字符串数组是否按字典序升序排列
     * @param arr 待验证数组
     */
    private static void assertIsSorted(String[] arr) {
        String previous = "";
        for (String s : arr) {
            assertTrue("排序失败：" + previous + " 应出现在 " + s + " 之前",
                    s.compareTo(previous) >= 0);
            previous = s;
        }
    }

    /* 基础功能测试 */
    @Test
    public void testEmptyArray() {
        String[] result = RadixSort.sort(emptyArray);
        assertArrayEquals("空数组应保持原样", emptyArray, result);
    }

    @Test
    public void testSingleElement() {
        String[] result = RadixSort.sort(singleElement);
        assertArrayEquals("单元素数组应保持不变", singleElement, result);
    }

    /* 核心功能测试 */
    @Test
    public void testSameLengthSorting() {
        String[] expected = {"apple", "banana", "cat", "dog"};
        String[] result = RadixSort.sort(Arrays.copyOf(sameLength, sameLength.length));
        assertArrayEquals("等长字符串字典序排序失败", expected, result);
    }

    @Test
    public void testVariableLengthSorting() {
        String[] expected = {"", "a", "ab", "abc", "abcd"};
        String[] result = RadixSort.sort(variableLength);
        System.out.println("变长排序结果: " + Arrays.toString(result));
        assertArrayEquals("变长字符串排序错误", expected, result);
    }

    /* 边界条件测试 */
    @Test
    public void testSpecialCharacters() {
        String[] expected = {"a", "a#", "a0", "a1", "a!"}; // ASCII顺序: !(33) < #(35) < 0(48) < 1(49)
        String[] result = RadixSort.sort(specialCharacters);
        System.out.println("特殊字符排序结果: " + Arrays.toString(result));
        assertIsSorted(result); // 双重验证
        assertArrayEquals("特殊字符排序错误", expected, result);
    }

    /* 异常情况测试 */
    @Test(expected = IllegalArgumentException.class)
    public void testNonASCIICharacters() {
        String[] invalidInput = {"中文", "test"};
        RadixSort.sort(invalidInput); // 应抛出异常
    }

    /* 稳定性测试 (需要扩展测试数据) */
    @Test
    public void testStableSorting() {
        String[] duplicates = {"apple", "apple2", "apple1", "apple"};
        String[] expected = {"apple", "apple", "apple1", "apple2"};
        String[] result = RadixSort.sort(duplicates);
        // 验证原始顺序保留：两个"apple"的相对位置
        assertSame("稳定性失败：相同前缀元素顺序被改变",
                duplicates[0], result[0]);
        assertSame("稳定性失败：相同前缀元素顺序被改变",
                duplicates[3], result[1]);
    }

    public static void main(String[] args) {
        jh61b.junit.TestRunner.runTests(RadixSortTester.class);
    }
}