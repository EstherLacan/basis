package com.jiangfw.huffman;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Util {

    public static int convertBytes2Int(byte[] bytes) {
        return (bytes[3] & 0x000000ff) + ((bytes[2] & 0x000000ff) << 8)
                + ((bytes[1] & 0x000000ff) << 16)
                + ((bytes[0] & 0x000000ff) << 24);

    }

    public static final boolean testPointInRect(int _x, int _y, int x, int y,
            int width, int height) {
        return _x > x && _x <= x + width && _y > y && _y < y + height;
    }

    public static final void debug(Object s) {
        System.out.println(s);
    }

    // Returns the contents of the file in a byte array.
    public static byte[] getBytesFromFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);

        // Get the size of the file
        long length = file.length();

        // You cannot create an array using a long type.
        // It needs to be an int type.
        // Before converting to an int type, check
        // to ensure that file is not larger than Integer.MAX_VALUE.
        if (length > Integer.MAX_VALUE) {
            return null;
        }

        // Create the byte array to hold the data
        byte[] bytes = new byte[(int) length];

        // Read in the bytes
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }

        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file "
                    + file.getName());
        }

        // Close the input stream and return bytes
        is.close();
        return bytes;
    }

    public final static String byteArrayToHexString(byte in[]) {
        byte ch = 0x00;
        int i = 0;
        if (in == null || in.length <= 0) {
            return null;
        }
        String pseudo[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                "A", "B", "C", "D", "E", "F"};
        StringBuffer out = new StringBuffer(in.length * 2);
        while (i < in.length) {
            ch = (byte) (in[i] & 0xF0); // Strip off high nibble
            ch = (byte) (ch >>> 4);
            // shift the bits down
            ch = (byte) (ch & 0x0F);
            // must do this is high order bit is on!
            out.append(pseudo[(int) ch]); // convert the nibble to a String
            // Character
            ch = (byte) (in[i] & 0x0F); // Strip off low nibble
            out.append(pseudo[(int) ch]); // convert the nibble to a String
            // Character
            i++;

        }
        String rslt = new String(out);
        return rslt;

    }

    public static byte[] int2Bytes(int value, int len) {
        byte[] bytes = new byte[len];
        for (int i = len - 1; i >= 0; i--) {
            bytes[i] = (byte) ((value >> (8 * (len - 1 - i))) & 0xff);
        }
        return bytes;
    }

    public static int bytes2Int(byte[] bytes) {
        return bytes2Int(bytes, 0, bytes.length);
    }

    public static int bytes2Int(byte[] bytes, int offset, int len) {
        int value = 0;
        for (int i = len - 1; i >= 0; i--) {
            int b = bytes[offset + i] & 0xff;
            value = (b << (8 * (len - 1 - i))) | value;
        }
        return value;
    }
}
