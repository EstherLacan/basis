package com.jiangfw.huffman;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class BitReader {

    private BufferedInputStream bis;
    private int readCount;
    private byte cacheByte;

    public BitReader(BufferedInputStream bis) {
        this.bis = bis;
        readCount = 8;
    }

    /**
     * @return 0 or 1,-1 means EOF
     */
    public byte readBit() {
        if (readCount == 8) {
            int value = -1;
            try {
                value = bis.read();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (value == -1) {
                return -1;
            }
            cacheByte = (byte) value;
            readCount = 0;
        }
        int mask = 0x80 >>> readCount;
        readCount++;
        return (byte) ((cacheByte & mask) >>> (8 - readCount));

    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        BitWriter bw = new BitWriter(128);
        bw.write(1, 1);
        bw.write(1, 1);
        bw.write(1, 1);
        byte[] data = bw.toByteArray();
        Util.debug("len = " + data.length + "  " + Util.byteArrayToHexString(data));
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        BitReader br = new BitReader(new BufferedInputStream(bais));
        byte b;
        while ((b = br.readBit()) != -1) {
            Util.debug("" + b);
        }

    }

}
