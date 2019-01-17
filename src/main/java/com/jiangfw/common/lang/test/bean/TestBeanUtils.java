package com.jiangfw.common.lang.test.bean;

import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;


public class TestBeanUtils {

    public static void main(String[] args) {
        Beans bean = new Beans();
        try {
            String pro = BeanUtils.getProperty(bean, "aa");
            String probb = BeanUtils.getProperty(bean, "bb");
            String date = BeanUtils.getProperty(bean, "date.year");
            String cal = BeanUtils.getProperty(bean, "cal.timeZone");
            String date1 = BeanUtils.getProperty(bean, "dates");
            BeanUtils.setProperty(bean, "str", "goodvalue");
//			BeanUtils.d
//		Date datec=	(Date) ConvertUtils.convert(date, Date.class);
            System.out.println(pro);
        } catch (IllegalAccessException e) {
            System.out.println("IllegalAccessException");
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            System.out.println("InvocationTargetException");
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            System.out.println("NoSuchMethodException。。。。");
            //e.printStackTrace();
        }
    }

}