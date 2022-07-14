package restart;

public class TryTest {
    public static void main(String[] args) {
        int bitMask = 1<<5;
        System.out.println(bitMask);
        System.out.println(Integer.toBinaryString(25));
        String str = Integer.toBinaryString(6 | bitMask);
        System.out.println(str);
    }
}
