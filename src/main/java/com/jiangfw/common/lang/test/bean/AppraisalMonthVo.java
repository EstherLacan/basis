package com.jiangfw.common.lang.test.bean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *<p>Description:月考勤表entity</p>
 *
 *
 * @author jiangfengwei
 * @since 2013-8-22
 */
public class AppraisalMonthVo{
	 /** 月登陆统计时间以秒为单位*/
    private long secCount;
    /** userId */
    private Integer userId;
    /** 姓名*/
    private String userName;
    /** 警号*/
    private String policeNumber;
    private JSONArray appList;
	/**
	 * @return the secCount
	 */
	public long getSecCount() {
		return secCount;
	}
	/**
	 * @param secCount the secCount to set
	 */
	public void setSecCount(long secCount) {
		this.secCount = secCount;
	}
	
	/**
	 * @return the appList
	 */
	public JSONArray getAppList() {
		return appList;
	}
	/**
	 * @param appList the appList to set
	 */
	public void setAppList(JSONArray appList) {
		this.appList = appList;
	}
	
	
	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the policeNumber
	 */
	public String getPoliceNumber() {
		return policeNumber;
	}
	/**
	 * @param policeNumber the policeNumber to set
	 */
	public void setPoliceNumber(String policeNumber) {
		this.policeNumber = policeNumber;
	}
	/**
	 *<p>Description:merge相同对象的考勤数据</p>
	 *
	 * @param day
	 *
	 * @author jiangfengwei
	 *
	 */
	public void mergeData(AppraisalDayVo day){
		//累加本月的考勤时间
		this.secCount=this.secCount+day.getSecCount();
		this.appList.add(day);
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}
	/* 
	 * userId 相同则可看作是相同的对象
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if(obj instanceof AppraisalDayVo){
			AppraisalDayVo other = (AppraisalDayVo) obj;
			if (this.userId == null) {
				if (other.getUserId() != null){
					return false;
				}
			} else if (!this.userId.equals(other.getUserId())){
				return false;
			}
		}else if(obj instanceof AppraisalMonthVo){
			AppraisalMonthVo other = (AppraisalMonthVo) obj;
			if (this.userId == null) {
				if (other.getUserId() != null){
					return false;
				}
			} else if (!this.userId.equals(other.getUserId())){
				return false;
			}
		}
		
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}
}
