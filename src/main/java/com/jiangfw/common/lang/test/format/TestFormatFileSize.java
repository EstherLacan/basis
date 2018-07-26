package com.jiangfw.common.lang.test.format;

import java.text.DecimalFormat;

public class TestFormatFileSize {

	public static void main(String[] args) {

		Double fileSize = 145853222d;
		String fileSizeStr = null;
		if (fileSize != null) {
			DecimalFormat df = new DecimalFormat("#.00");
			if (fileSize < 1024) {
				fileSizeStr = df.format((double) fileSize) + "B";
			} else if (fileSize < 1048576) {
				fileSizeStr = df.format((double) fileSize / 1024) + "K";
			} else if (fileSize < 1073741824) {
				fileSizeStr = df.format((double) fileSize / 1048576) + "M";
			} else {
				fileSizeStr = df.format((double) fileSize / 1073741824) + "G";
			}
		}
		System.out.println(fileSizeStr);
	}

}
