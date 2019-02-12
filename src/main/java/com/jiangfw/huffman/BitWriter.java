package com.jiangfw.huffman;

public class BitWriter {

    // -- Fields --

    /**
     * Buffer storing all bits written thus far.
     */
    private byte[] buf;

    /**
     * Byte index into the buffer.
     */
    private int index;

    /**
     * Bit index into current byte of the buffer.
     */
    private int bit;

    // -- Constructors --

    /**
     * Constructs a new bit writer.
     */
    public BitWriter() {
        this(10);
    }

    /**
     * Constructs a new bit writer with the given initial buffer size.
     */
    public BitWriter(int size) {
        buf = new byte[size];
    }

    // -- BitWriter API methods --

    /**
     * Writes the given value using the given number of bits.
     */
    public void write(int value, int numBits) {
        byte[] bits = new byte[numBits];
        for (int i = 0; i < numBits; i++) {
            bits[i] = (byte) (value & 0x0001);
            value >>= 1;
        }
        for (int i = numBits - 1; i >= 0; i--) {
            int b = bits[i] << (7 - bit);
            buf[index] |= b;
            bit++;
            if (bit > 7) {
                bit = 0;
                index++;
                if (index >= buf.length) {
                    // buffer is full; increase the size
                    byte[] newBuf = new byte[buf.length * 2];
                    System.arraycopy(buf, 0, newBuf, 0, buf.length);
                    buf = newBuf;
                }
            }
        }
    }

    /**
     * Gets an array containing all bits written thus far.
     */
    public byte[] toByteArray() {
        int size = index;
        if (bit > 0) {
            size++;
        }
        byte[] b = new byte[size];
        System.arraycopy(buf, 0, b, 0, size);
        return b;
    }

    // -- Main method --

    /**
     * Tests the BitWriter class.
     */
    public static void main(String[] args) {
        BitWriter bw = new BitWriter(128);
        bw.write(1, 1);
        bw.write(1, 1);
        bw.write(1, 1);
        byte[] data = bw.toByteArray();
        Util.debug("len = " + data.length + "  " + Util.byteArrayToHexString(data));
    }
}

