package com.pk.assistant;

public class UserDetails {

	private String username;
	private String password;

	private String orgName;

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getReciversName() {
		return reciversName;
	}

	public void setReciversName(String reciversName) {
		this.reciversName = reciversName;
	}

	public String getReceiversEmail() {
		return receiversEmail;
	}

	@Override
	public String toString() {
		return "UserDetails [username=" + username + ", password=" + password + ", orgName=" + orgName
				+ ", reciversName=" + reciversName + ", receiversEmail=" + receiversEmail + ", customSubject="
				+ customSubject + ", designation=" + designation + "]";
	}

	public void setReceiversEmail(String receiversEmail) {
		this.receiversEmail = receiversEmail;
	}

	public String getCustomSubject() {
		return customSubject;
	}

	public void setCustomSubject(String customSubject) {
		this.customSubject = customSubject;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	private String reciversName;
	private String receiversEmail;
	private String customSubject;
	private String designation;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
