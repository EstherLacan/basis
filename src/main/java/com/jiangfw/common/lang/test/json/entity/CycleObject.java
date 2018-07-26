package com.jiangfw.common.lang.test.json.entity;

/**
 *<p>Description:子包含类</p>
 *
 *
 * @author jiangfengwei
 * @since 2013-8-27
 */
public class CycleObject {

	    private String      memberId;
	    private String      sex;
	    private CycleObject me = this;
		/**
		 * @return the memberId
		 */
		public String getMemberId() {
			return memberId;
		}
		/**
		 * @param memberId the memberId to set
		 */
		public void setMemberId(String memberId) {
			this.memberId = memberId;
		}
		/**
		 * @return the sex
		 */
		public String getSex() {
			return sex;
		}
		/**
		 * @param sex the sex to set
		 */
		public void setSex(String sex) {
			this.sex = sex;
		}
		/**
		 * @return the me
		 */
		public CycleObject getMe() {
			return me;
		}
		/**
		 * @param me the me to set
		 */
		public void setMe(CycleObject me) {
			this.me = me;
		}
	    
}
