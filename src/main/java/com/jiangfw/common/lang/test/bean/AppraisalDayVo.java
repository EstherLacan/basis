package com.jiangfw.common.lang.test.bean;

import java.util.Date;

import net.sf.json.JSONObject;

/**
 *<p>Description:日考勤表entity</p>
 *
 *
 * @author jiangfengwei
 * @since 2013-8-22
 */
public class AppraisalDayVo{
	 /** 每日登陆时间以秒为单位*/
    private long secCount;
    /** 登录时间*/
    private Date appraisalDate; 
    /** userId */
    private Integer userId;
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
	 * @return the appraisalDate
	 */
	public Date getAppraisalDate() {
		return appraisalDate;
	}
	/**
	 * @param appraisalDate the appraisalDate to set
	 */
	public void setAppraisalDate(Date appraisalDate) {
		this.appraisalDate = appraisalDate;
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
				if (other.userId != null){
					return false;
				}
			} else if (!this.userId.equals(other.userId)){
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
