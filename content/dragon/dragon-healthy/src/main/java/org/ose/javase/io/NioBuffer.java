package org.ose.javase.io;

import java.nio.Buffer;
import java.nio.CharBuffer;
import java.util.Arrays;

public class NioBuffer {
    public static void main(String[] args) {
        // Allocation
        CharBuffer buff1 = CharBuffer.allocate(30);
        // Wrapping
        char[] a = new char[30];
        int offset = 10;
        int length = 20;
        CharBuffer buff2 = CharBuffer.wrap(a, offset, length);

        // put(), get(), mark(), reset()
        buff2.mark();
        System.out.println("1: " + buffInfo(buff2));
        buff2.put('h'); // (relative) put and increment position
        System.out.println("2: " + buffInfo(buff2));
        buff2.put(11, 'i'); // (absolute) put
        System.out.println("3: " + buffInfo(buff2));
        buff2.reset();
        System.out.println(buff2.get()); // (relative) get and increment position
        System.out.println("4: " + buffInfo(buff2));
        System.out.println(buff2.get(11)); // (absolute) get
        System.out.println("5: " + buffInfo(buff2));
        buff2.position(buff2.position() + 1);

        // flip(), hasRemaining(), remaining()
        buff2.flip();
        System.out.println("6: " + buffInfo(buff2));
        buff2.position(offset);
        buff2.mark();
        while (buff2.hasRemaining()) {
            System.out.println(buff2.get());
        }
        buff2.reset();
        int count = buff2.remaining();
        for (int i = 0; i < count; i++) {
            System.out.println(buff2.get());
        }

        // clear(), put(), get()
        buff2.clear();
        System.out.println("7: " + buffInfo(buff2));
        char[] b = new char[30];
        buff2.get(b, 0, buff2.remaining()); // buffer -> array
        System.out.println("8: " + buffInfo(buff2));
        System.out.println(Arrays.toString(b));
        buff2.clear();
        char[] c = { 'g', 'o', 'o', 'd', '!' };
        buff2.put(c, 0, c.length); // buffer <- array 
        System.out.println("9: " + buffInfo(buff2));
        System.out.println(Arrays.toString(a));
        buff2.clear();
        buff2.put(buff1); // buff2 <- buff1
        System.out.println("10: " + buffInfo(buff2));
        System.out.println(Arrays.toString(a));
    }

    private static String buffInfo(Buffer buffer) {
        return String.format("[position=%d limit=%d capacity=%d]", buffer.position(),
            buffer.limit(), buffer.capacity());
    }
}
