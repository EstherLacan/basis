package com.jiangfw.common.lang.test.format;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TestFormatTime {

	public static void main(String[] args) {

		Calendar c1 = new GregorianCalendar(2007, 1, 16, 10, 12, 14);
		Calendar c2 = new GregorianCalendar(2007, 1, 16, 15, 20, 35);
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");

		// formatter.setTimeZone(TimeZone.getTimeZone("GMT+0:00"));
		long l = c2.getTimeInMillis() - c1.getTimeInMillis();
		System.out.println("秒数:" + l);
		System.out.println("时分秒:" + formatter.format(l));

//		String aab = DataUtils.dataformat("20140923162220", "yyyyMMddHHmmss");
//		try {
//			Date aba = DataUtils.parseDate("20140923162220", "yyyyMMddHHmmss");
//			String aaaab = DataUtils.date2Str(aba, DataUtils.datetimeFormat);
//			System.out.println(aaaab);
//		} catch (ParseException e1) {
//			e1.printStackTrace();
//		}
//		System.out.println(aab);

	}

}
