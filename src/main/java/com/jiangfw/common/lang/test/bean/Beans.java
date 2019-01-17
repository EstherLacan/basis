package com.jiangfw.common.lang.test.bean;

import java.util.Calendar;
import java.util.Date;

public class Beans {

    public int aa = 11;
    private Integer bb = 22;
    private Date date = new Date();
    private String str = "str...ing";
    private Date[] dates = {new Date(), new Date(2013)};
    private Calendar cal = Calendar.getInstance();

    /**
     * @return the aa
     */
    public int getAa() {
        return aa;
    }

    /**
     * @param aa the aa to set
     */
    public void setAa(int aa) {
        this.aa = aa;
    }

    /**
     * @return the bb
     */
    public Integer getBb() {
        return bb;
    }

    /**
     * @param bb the bb to set
     */
    public void setBb(Integer bb) {
        this.bb = bb;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the str
     */
    public String getStr() {
        return str;
    }

    /**
     * @param str the str to set
     */
    public void setStr(String str) {
        this.str = str;
    }

    /**
     * @return the cal
     */
    public Calendar getCal() {
        return cal;
    }

    /**
     * @param cal the cal to set
     */
    public void setCal(Calendar cal) {
        this.cal = cal;
    }

    /**
     * @return the dates
     */
    public Date[] getDates() {
        return dates;
    }

    /**
     * @param dates the dates to set
     */
    public void setDates(Date[] dates) {
        this.dates = dates;
    }
}